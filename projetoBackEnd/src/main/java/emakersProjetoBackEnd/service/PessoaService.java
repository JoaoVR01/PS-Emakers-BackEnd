package emakersProjetoBackEnd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emakersProjetoBackEnd.data.dto.request.PessoaRequestDTO;
import emakersProjetoBackEnd.data.dto.response.PessoaResponseDTO;
import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    //método que retorna todas as pessoas
    public List<PessoaResponseDTO> getAllPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
    }

    //método que retorna uma pessoa por id
    public PessoaResponseDTO getPessoaById(Long idPessoa){
        Pessoa pessoa = findPessoa(idPessoa);

        return new PessoaResponseDTO(pessoa);
    }

    //método qeu adiciona uma nova pessoa
    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = new Pessoa(pessoaRequestDTO);
        pessoaRepository.save(pessoa);
        return new PessoaResponseDTO(pessoa);
    }

    //método que atualiza as informações de uma pessoa
    public PessoaResponseDTO updatePessoa(PessoaRequestDTO pessoaRequestDTO, Long idPessoa){
        Pessoa pessoa = findPessoa(idPessoa);
        pessoa.setName(pessoaRequestDTO.name());
        pessoa.setCpf(pessoaRequestDTO.cpf());
        pessoa.setCep(pessoaRequestDTO.cep());
        pessoa.setEmail(pessoaRequestDTO.email());
        pessoa.setSenha(pessoaRequestDTO.senha());
        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    //método que deleta uma pessoa
    public String deletePessoa(Long idPessoa){
        Pessoa pessoa = findPessoa(idPessoa);
        pessoaRepository.delete(pessoa);
        return "Pessoa: " + idPessoa + " has been deleted";
    }

    private Pessoa findPessoa(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(() -> new RuntimeException("Pessoa not found"));
    }
}
