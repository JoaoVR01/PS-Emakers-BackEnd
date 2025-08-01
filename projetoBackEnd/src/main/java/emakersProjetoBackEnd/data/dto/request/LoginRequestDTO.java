package emakersProjetoBackEnd.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record LoginRequestDTO(
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email no formato inválido")
    String email,

    @NotBlank(message = "Password is required")
    String senha
) {

}
