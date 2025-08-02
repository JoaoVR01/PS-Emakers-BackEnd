package emakersProjetoBackEnd.data.dto.request;

import emakersProjetoBackEnd.data.entity.Livro;

public record EmprestimoRequestDTO(
    Livro livro
) {

}
