package emakersProjetoBackEnd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import emakersProjetoBackEnd.data.dto.request.AdminPessoaRequestDTO;
import emakersProjetoBackEnd.data.dto.response.CepResponseDTO;
import emakersProjetoBackEnd.data.dto.response.AdminPessoaResponseDTO;
import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.exceptions.authentication.InvalidRegisterException;
import emakersProjetoBackEnd.exceptions.general.EntityNotFoundException;
import emakersProjetoBackEnd.exceptions.pessoas.InvalidPessoaDeletionException;
import emakersProjetoBackEnd.repository.EmprestimoRepository;
import emakersProjetoBackEnd.repository.PessoaRepository;


@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private EmprestimoRepository emprestimoRepository;


    //método que retorna todas as pessoas
    public List<AdminPessoaResponseDTO> getAllPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(AdminPessoaResponseDTO::new).collect(Collectors.toList());
    }

    //método que retorna uma pessoa por id
    public AdminPessoaResponseDTO getPessoaById(Long idPessoa){
        Pessoa pessoa = findPessoa(idPessoa);

        return new AdminPessoaResponseDTO(pessoa);
    }

    //método que adiciona uma nova pessoa
    public AdminPessoaResponseDTO createPessoa(AdminPessoaRequestDTO adminPessoaRequestDTO){
        if(pessoaRepository.findByEmail(adminPessoaRequestDTO.email()) != null ){
            throw new InvalidRegisterException("Email já cadastrado no sistema");
        }
        
        if(pessoaRepository.findByCpf(adminPessoaRequestDTO.cpf()) != null ){
            throw new InvalidRegisterException("CPF já cadastrado no sistema");
        }
            Pessoa pessoa = new Pessoa(adminPessoaRequestDTO);
            
            //senha padrão
            pessoa.setSenha("111");
            String encryptedPassword = new BCryptPasswordEncoder().encode(pessoa.getSenha());
            pessoa.setSenha(encryptedPassword);

            CepResponseDTO cepResponseDTO = cepService.consultaCep(pessoa.getCep());
            pessoa.setLogradouro(cepResponseDTO.logradouro());
            pessoa.setBairro(cepResponseDTO.bairro());
            pessoa.setComplemento(cepResponseDTO.complemento());
            pessoa.setUf(cepResponseDTO.uf());
            pessoa.setLocalidade(cepResponseDTO.localidade());
            pessoaRepository.save(pessoa);
            return new AdminPessoaResponseDTO(pessoa);
    }

    //método que atualiza as informações de uma pessoa
    public AdminPessoaResponseDTO updatePessoa(AdminPessoaRequestDTO adminPessoaRequestDTO, Long idPessoa){
        Pessoa pessoa = findPessoa(idPessoa);

        if(pessoaRepository.existsByEmail(adminPessoaRequestDTO.email())){
            throw new InvalidRegisterException("Email já cadastrado no sistema");
        }

        if(pessoaRepository.existsByCpf(adminPessoaRequestDTO.cpf())){
            throw new InvalidRegisterException("CPF já cadastrado no sistema");
        }

        pessoa.setName(adminPessoaRequestDTO.name());
        pessoa.setCpf(adminPessoaRequestDTO.cpf());

        pessoa.setCep(adminPessoaRequestDTO.cep());
        CepResponseDTO cepResponseDTO = cepService.consultaCep(pessoa.getCep());
        pessoa.setLogradouro(cepResponseDTO.logradouro());
        pessoa.setBairro(cepResponseDTO.bairro());
        pessoa.setComplemento(cepResponseDTO.complemento());
        pessoa.setUf(cepResponseDTO.uf());            
        pessoa.setLocalidade(cepResponseDTO.localidade());
        pessoa.setEmail(adminPessoaRequestDTO.email());

        pessoaRepository.save(pessoa);

        return new AdminPessoaResponseDTO(pessoa);
    }

    //método que deleta uma pessoa
    public String deletePessoa(Long idPessoa){
        Pessoa pessoa = findPessoa(idPessoa);
        if(emprestimoRepository.existsByPessoaAndStatusTrue(pessoa)){
            throw new InvalidPessoaDeletionException(pessoa.getIdPessoa());
        }

        emprestimoRepository.deleteAllByPessoa(pessoa);
        pessoaRepository.delete(pessoa);

        return "Pessoa: " + idPessoa + " has been deleted";
    }

    private Pessoa findPessoa(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(() -> new EntityNotFoundException(idPessoa));
    }
}