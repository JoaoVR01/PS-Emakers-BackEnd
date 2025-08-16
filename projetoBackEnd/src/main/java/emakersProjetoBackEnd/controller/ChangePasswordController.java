package emakersProjetoBackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import emakersProjetoBackEnd.data.dto.request.ChangePasswordRequestDTO;
import emakersProjetoBackEnd.service.ChangePasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/password")
@Tag(name = "Mudar a senha", description = "Endpoints relacionados à gestão de senhas do usuário.")
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @Operation(summary = "Altera a senha de um usuario")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Senha Alterada com sucesso", 
            content = @Content(
                mediaType = "text/plain",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = "Senha alterada com sucesso"
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "500", 
            description = "Erro interno do servidor",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 500",
                        value = """
                                """
                    )
                }
                
            )
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Token inválido ou cargo sem altorização",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 403",
                        value = """
                                """
                    )
                }
                
            )
            ),
        @ApiResponse(
            responseCode = "404", 
            description = "Senha atual incorreta",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 404 com senha atual sendo \"111\" ",
                        value = """
                        {
	"status": "NOT_FOUND",
	"message": "Senha Incorreta!",
	"timestamp": "2025-08-15T19:58:04.179+00:00"
}
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "400", 
            description = "Senha atual e/ou nova senha em branco",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 400 com senhaAtual em branco",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "A senha atual não pode der nula",
		"timestamp": "2025-08-15T19:56:38.775+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400 com newSenha em branco",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "A nova senha não pode ser nula",
		"timestamp": "2025-08-15T19:57:08.185+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400 com ambos os campos em branco",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "A senha atual não pode der nula",
		"timestamp": "2025-08-15T19:57:33.472+00:00"
	},
	{
		"status": "BAD_REQUEST",
		"message": "A nova senha não pode ser nula",
		"timestamp": "2025-08-15T19:57:33.472+00:00"
	}
]
                                """
                    ),

                }
                
            )),
        @ApiResponse(
            responseCode = "409", 
            description = "A nova senha não pode ser igual a atual",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 409",
                        value = """
                        {
	"status": "CONFLICT",
	"message": "A nova senha não pode ser igual a atual",
	"timestamp": "2025-08-14T14:36:52.077+00:00"
}
                                """
                    )
                }
                
            ))
    })
    @PostMapping("/changepassword")
    public ResponseEntity<String> changePassword(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Senhas", required = true)    
        @Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
            
            return ResponseEntity.status(HttpStatus.OK).body(changePasswordService.changeSenha(changePasswordRequestDTO));
    }


}
