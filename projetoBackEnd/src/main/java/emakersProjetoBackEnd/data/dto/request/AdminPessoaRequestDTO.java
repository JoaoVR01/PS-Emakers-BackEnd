package emakersProjetoBackEnd.data.dto.request;

import emakersProjetoBackEnd.data.entity.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdminPessoaRequestDTO(
    @NotBlank(message = "Name is required")
    String name,

    @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    String cpf,

    @Pattern(regexp = 	"^\\d{8}$", message = "o CEP deve estar no formato 11111111")
    String cep,

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email no formato inválido")
    String email,
    
    Roles role
) {
    
}
