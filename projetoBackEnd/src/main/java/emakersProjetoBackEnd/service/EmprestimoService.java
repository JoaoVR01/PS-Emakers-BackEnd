package emakersProjetoBackEnd.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import emakersProjetoBackEnd.data.dto.request.EmprestimoRequestDTO;
import emakersProjetoBackEnd.data.dto.response.EmprestimoResponseDTO;
import emakersProjetoBackEnd.data.entity.Emprestimo;
import emakersProjetoBackEnd.data.entity.Livro;
import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.exceptions.emprestimo.DevolucaoInvalidaException;
import emakersProjetoBackEnd.exceptions.emprestimo.LivroEmprestadoException;
import emakersProjetoBackEnd.exceptions.general.EntityNotFoundException;
import emakersProjetoBackEnd.repository.EmprestimoRepository;
import emakersProjetoBackEnd.repository.LivroRepository;
import emakersProjetoBackEnd.repository.PessoaRepository;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    //mostra os emprestimos ativos
    public List<EmprestimoResponseDTO> getAllemprestimos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findByStatusTrue();
        return emprestimos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }

    //mostra o registro de devoluções
    public List<EmprestimoResponseDTO> getAlldevolucoes(){
        List<Emprestimo> devolucoes = emprestimoRepository.findByStatusFalse();
        return devolucoes.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }

    //método de emprestar
    public EmprestimoResponseDTO emprestar(EmprestimoRequestDTO emprestimoRequestDTO){
        Livro livro = livroRepository.findByName(emprestimoRequestDTO.livro().getName());

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Pessoa pessoa = pessoaRepository.findByEmail(email);

        boolean status = emprestimoRepository.existsByLivroAndStatusTrue(livro);

        if(status){
            throw new LivroEmprestadoException(livro.getName());
       }else{
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setLivro(livro);
            emprestimo.setPessoa(pessoa);
            emprestimo.setDataEmprestimo(LocalDate.now());
            emprestimo.setStatus(true);
            emprestimoRepository.save(emprestimo);

            return new EmprestimoResponseDTO(emprestimo);
        }
    }

    //método de devolução
    public EmprestimoResponseDTO devolver(EmprestimoRequestDTO emprestimoRequestDTO){
        Livro livro = livroRepository.findByName(emprestimoRequestDTO.livro().getName());
        if(livro == null){
            throw new EntityNotFoundException(null);
        }

        boolean status = emprestimoRepository.existsByLivroAndStatusTrue(livro);

        if(status){
            Emprestimo emprestimo = emprestimoRepository.findByLivroAndStatusTrue(livro);
            emprestimo.setDataDevolucao(LocalDate.now());
            emprestimo.setStatus(false);
            emprestimoRepository.save(emprestimo);

            return new EmprestimoResponseDTO(emprestimo);
        }else{
             var authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            throw new DevolucaoInvalidaException(livro.getName(), email);
        }
        
    }
}
