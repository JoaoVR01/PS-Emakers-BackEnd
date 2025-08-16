package emakersProjetoBackEnd.data.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDTO(

    @NotBlank(message = "Title is required")
    String name,

    @NotBlank(message = "Autor is required")
    String autor,

    @NotNull(message = "Release date is required")
    LocalDate data_lancamento
) {
    
}
