package emakersProjetoBackEnd.data.dto.request;

import emakersProjetoBackEnd.data.entity.Livro;
import emakersProjetoBackEnd.data.entity.Pessoa;

public record EmprestimoRequestDTO(
    Pessoa pessoa,

    Livro livro
) {

}
