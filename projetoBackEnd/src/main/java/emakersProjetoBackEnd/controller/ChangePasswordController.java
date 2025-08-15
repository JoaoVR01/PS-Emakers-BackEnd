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
import io.swagger.v3.oas.annotations.media.Schema;
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
                mediaType = "aplication/jason",
                schema = @Schema(implementation = ChangePasswordRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                        {
                            "senhaAtual" : "senha",
                            "newSenha" : "novasenha"

                        }
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "500", 
            description = "Erro interno do servidor"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Token inválido ou cargo sem altorização"
            ),
        @ApiResponse(
            responseCode = "404", 
            description = "Senha atual incorreta",
            content = @Content(
                mediaType = "aplication/jason",
                schema = @Schema(implementation = ChangePasswordRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 404 com senha atual sendo \"1234\" ",
                        value = """
                        {
                            "senhaAtual" : "12345",
                            "newSenha" : "novasenha"

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
                schema = @Schema(implementation = ChangePasswordRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 400 com senhaAtual em branco",
                        value = """
                        {
                            "senhaAtual" : "",
                            "newSenha" : "novasenha"

                        }
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400 com newSenha em branco",
                        value = """
                        {
                            "senhaAtual" : "senha",
                            "newSenha" : ""

                        }
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400 com ambos os campos em branco",
                        value = """
                        {
                            "senhaAtual" : "",
                            "newSenha" : ""

                        }
                                """
                    ),

                }
                
            )),
        @ApiResponse(
            responseCode = "409", 
            description = "A nova senha não pode ser igual a atual",
            content = @Content(
                mediaType = "aplication/jason",
                schema = @Schema(implementation = ChangePasswordRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 409",
                        value = """
                        {
                            "senhaAtual" : "novasenha",
                            "newSenha" : "novasenha"

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
