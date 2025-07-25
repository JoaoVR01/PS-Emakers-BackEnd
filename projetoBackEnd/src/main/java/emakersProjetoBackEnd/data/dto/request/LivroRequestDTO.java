package emakersProjetoBackEnd.data.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public record LivroRequestDTO(

    @NotBlank(message = "Title is required")
    String nome,

    @NotBlank(message = "Autor is required")
    String autor,

    @NotBlank(message = "release date is required")
    Date data_lancamento
) {
    
}
