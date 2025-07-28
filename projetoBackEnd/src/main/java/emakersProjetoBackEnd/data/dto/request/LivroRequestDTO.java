package emakersProjetoBackEnd.data.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record LivroRequestDTO(

    @NotBlank(message = "Title is required")
    String name,

    @NotBlank(message = "Autor is required")
    String autor,

    @NotBlank(message = "release date is required")
    LocalDate data_lancamento
) {
    
}
