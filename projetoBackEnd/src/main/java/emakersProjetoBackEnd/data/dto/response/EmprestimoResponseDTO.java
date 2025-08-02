package emakersProjetoBackEnd.data.dto.response;

import java.time.LocalDate;

import emakersProjetoBackEnd.data.entity.Emprestimo;

public record EmprestimoResponseDTO(
    Long idEmprestimo,

    PessoaResponseDTO pessoaResponseDTO,

    LivroResponseDTO livroResponseDTO,

    LocalDate dataEmprestimo,

    LocalDate dataDevolucao,

    boolean status

) {
    public EmprestimoResponseDTO(Emprestimo emprestimo){
        this(
            emprestimo.getIdEmprestimo(), 
            new PessoaResponseDTO(emprestimo.getPessoa()), 
            new LivroResponseDTO(emprestimo.getLivro()),
            emprestimo.getDataEmprestimo(),
            emprestimo.getDataDevolucao(),
            emprestimo.isStatus()
            );
    }

}
