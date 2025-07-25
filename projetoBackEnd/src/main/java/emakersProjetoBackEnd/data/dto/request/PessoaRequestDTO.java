package emakersProjetoBackEnd.data.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDTO(

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "CPF is required")
    String cpf,

    @NotBlank(message = "CEP is required")
    String cep,

    @NotBlank(message = "Email is required")
    String email,

    @NotBlank(message = "Password is required")
    String senha
) {

}
