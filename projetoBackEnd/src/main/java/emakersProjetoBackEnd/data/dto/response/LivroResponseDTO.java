package emakersProjetoBackEnd.data.dto.response;

import java.time.LocalDate;

import emakersProjetoBackEnd.data.entity.Livro;

public record LivroResponseDTO(
    Long id,

    String name,

    String autor,

    LocalDate anoLancamento
) {
    public LivroResponseDTO(Livro livro){
        this(livro.getIdLivro(), livro.getName(), livro.getAutor(), livro.getData_lancamento());
    }
}
