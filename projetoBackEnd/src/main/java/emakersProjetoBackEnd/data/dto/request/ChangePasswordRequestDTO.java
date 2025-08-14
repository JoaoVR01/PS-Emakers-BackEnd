package emakersProjetoBackEnd.data.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequestDTO(

    @NotBlank(message = "A senha atual não pode der nula")
    String senhaAtual,

    @NotBlank(message = "A nova senha não pode ser nula")
    String newSenha
) {

}
