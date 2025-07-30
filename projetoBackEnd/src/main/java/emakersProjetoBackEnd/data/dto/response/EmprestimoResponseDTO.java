package emakersProjetoBackEnd.data.dto.response;

import emakersProjetoBackEnd.data.entity.Emprestimo;

public record EmprestimoResponseDTO(
    Long idEmprestimo,

    PessoaResponseDTO pessoaResponseDTO,

    LivroResponseDTO livroResponseDTO

) {
    public EmprestimoResponseDTO(Emprestimo emprestimo){
        this(emprestimo.getIdEmprestimo(), new PessoaResponseDTO(emprestimo.getPessoa()), new LivroResponseDTO(emprestimo.getLivro()));
    }

}
