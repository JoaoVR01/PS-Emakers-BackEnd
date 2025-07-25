package emakersProjetoBackEnd.data.dto.response;

import java.util.Date;

import emakersProjetoBackEnd.data.entity.Livro;

public record LivroResponseDTO(
    Long id,

    String nome,

    String autor,

    Date anoLancamento
) {
    public LivroResponseDTO(Livro livro){
        this(livro.getIdLivro(), livro.getName(), livro.getAutor(), livro.getData_lancamento());
    }
}
