package emakersProjetoBackEnd.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import emakersProjetoBackEnd.data.dto.request.AdminPessoaRequestDTO;
import emakersProjetoBackEnd.data.dto.response.AdminPessoaResponseDTO;
import emakersProjetoBackEnd.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/pessoa") //informa que todo endpoint /livro conterá um método que modifica um livro
@Tag(name = "Pessoas", description = "Endpoints relacionados à gestão de pessoas.")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService; //necessário para acessar os métodos criados no service

    //chamar os método e criar os endpoints

    //retorna todas as pessoas
    @Operation(summary = "Lista todos as pessoas do banco de Dados")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Pessoas listadas com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                            [
	{
		"id": 3,
		"nome": "ADMIN1",
		"cpf": "12345678900",
		"cep": "35501021",
		"logradouro": "Rua Américo Martins",
		"complemento": "",
		"bairro": "Esplanada",
		"localidade": "Divinópolis",
		"uf": "MG",
		"email": "admin1@gmail.com",
		"senha": "$2a$10$Kj2G8Xl/e2Vv3fgFTYebN.OjyyQs5q99iitPXdUyniQw9HJWwUWSe",
		"role": "ADMIN"
	},
	{
		"id": 4,
		"nome": "Usuario1",
		"cpf": "12325778926",
		"cep": "35501021",
		"logradouro": "Rua Américo Martins",
		"complemento": "",
		"bairro": "Esplanada",
		"localidade": "Divinópolis",
		"uf": "MG",
		"email": "user1@gmail.com",
		"senha": "$2a$10$JvBDW9UMY9oSjeVfQQGuyeXeQHJ9yddypKnCQC.rn.4DlKnX37f22",
		"role": "USER"
	},
	{
		"id": 5,
		"nome": "Usuario2",
		"cpf": "12325778927",
		"cep": "35501021",
		"logradouro": "Rua Américo Martins",
		"complemento": "",
		"bairro": "Esplanada",
		"localidade": "Divinópolis",
		"uf": "MG",
		"email": "user2@gmail.com",
		"senha": "$2a$10$boyv9VCbu5dxW5rKzGhS3OAyTFNqn5KQQJRWRsq6JB2TGZFSDbC46",
		"role": "USER"
	},
	{
		"id": 6,
		"nome": "Teste da Silva",
		"cpf": "14461541798",
		"cep": "00000000",
		"logradouro": null,
		"complemento": null,
		"bairro": null,
		"localidade": null,
		"uf": null,
		"email": "silva@gmail.com",
		"senha": "$2a$10$s9bOfLp7LzAeveFsqOSqH.4nnrTEfI7SAQ5pBdu42MLhksGgg0LoC",
		"role": "USER"
	},
	{
		"id": 7,
		"nome": "Pessoa Da Silva Santos",
		"cpf": "12345678912",
		"cep": "30110001",
		"logradouro": "Avenida do Contorno",
		"complemento": "até 1191 - lado ímpar",
		"bairro": "Centro",
		"localidade": "Belo Horizonte",
		"uf": "MG",
		"email": "dasilva@gmail.com",
		"senha": "$2a$10$gnIpy0o5DCQ5hid4ZyrGd.7.y6jol0O7z15GTHwfFollKU/0pNoL.",
		"role": "USER"
	},
	{
		"id": 8,
		"nome": "Teste da Silva Jr Jr",
		"cpf": "14461541792",
		"cep": "35501021",
		"logradouro": "Rua Américo Martins",
		"complemento": "",
		"bairro": "Esplanada",
		"localidade": "Divinópolis",
		"uf": "MG",
		"email": "silvajrjr@gmail.com",
		"senha": "$2a$10$stK9LWxZOwdYg37J88GO5.sbu5yXcEt1ynZpBm8sJx8I1O2ExCh9C",
		"role": "USER"
	}
]
                                """
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
            )),
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
            ))
    })
    @GetMapping(value = "/getall")
    public ResponseEntity<List<AdminPessoaResponseDTO>> getAllPessoas(){

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    //retorna uma pessoa por id
    @Operation(summary = "Busca uma pessoa por ID")
     @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Pessoa encontrada",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                            {
	"id": 4,
	"nome": "Usuario1",
	"cpf": "12325778926",
	"cep": "35501021",
	"logradouro": "Rua Américo Martins",
	"complemento": "",
	"bairro": "Esplanada",
	"localidade": "Divinópolis",
	"uf": "MG",
	"email": "user1@gmail.com",
	"senha": "$2a$10$JvBDW9UMY9oSjeVfQQGuyeXeQHJ9yddypKnCQC.rn.4DlKnX37f22",
	"role": "USER"
}
                                """
                    )
                }
            )),
        @ApiResponse(
            responseCode = "404", 
            description = "Entidade não encontrada com id: {idPessoa}",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 404",
                        value = """
                            {
	"status": "NOT_FOUND",
	"message": "Entity not found with id: 1",
	"timestamp": "2025-08-15T15:00:38.397+00:00"
}                        
                                """
                    )
                }
            )),
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
            ))
    })
    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<AdminPessoaResponseDTO> getLivroById(
        @Parameter(description = "ID da pessoa a ser buscada", example = "1")
        @PathVariable Long idPessoa) {

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaById(idPessoa));
    }

    //adiciona uma nova pessoa
    @Operation(summary = "Cadastra um novo livro")
     @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Pessoa criada com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 201",
                        value = """
                        {
	"id": 7,
	"nome": "Pessoa Da Silva Santos",
	"cpf": "12345678912",
	"cep": "30110001",
	"logradouro": "Avenida do Contorno",
	"complemento": "até 1191 - lado ímpar",
	"bairro": "Centro",
	"localidade": "Belo Horizonte",
	"uf": "MG",
	"email": "dasilva@gmail.com",
	"senha": "$2a$10$Mc/v0C5LnbMS5idClqXtHuVeBFPaJGh.ezx08edgO7VAiJ4UeXR8S",
	"role": "USER"
}
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "400", 
            description = "Dados fora das expressões regulares",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 400, com Nome fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Name is required",
		"timestamp": "2025-08-15T15:06:08.670+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com CPF fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "O CPF deve conter exatamente 11 dígitos numéricos.",
		"timestamp": "2025-08-15T15:06:44.230+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com CEP fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "o CEP deve estar no formato 11111111",
		"timestamp": "2025-08-15T15:07:20.201+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com email fora da expressão regular",
                        value = """
                            [
	{
		"status": "BAD_REQUEST",
		"message": "Email no formato inválido",
		"timestamp": "2025-08-15T15:08:43.635+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com senha fora da expressão regular",
                        value = """
                        {
                            	"name" : "Teste da Silva",
	                            "cpf" : "14461541801",
	                            "cep" : "35501021",
	                            "email" : "silvajr@gmail.com",
	                            "role" : ""
                        }
                                """
                    )

                }
                
            )),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(value = "/create")
    public ResponseEntity<AdminPessoaResponseDTO> createLivro(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da pessoa", required = true)
        @Valid @RequestBody AdminPessoaRequestDTO pessoaRequestDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaRequestDTO));
    }


    //atualiza os dados de uma pessoa existente
    @Operation(summary = "Atualiza os dados de uma pessoa existente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Pessoa atualizada com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 201, com id encontrado no banco de dados",
                        value = """
                       {
	"id": 10,
	"nome": "Pessoa Da Silva Santos",
	"cpf": "12345678919",
	"cep": "30110001",
	"logradouro": "Avenida do Contorno",
	"complemento": "até 1191 - lado ímpar",
	"bairro": "Centro",
	"localidade": "Belo Horizonte",
	"uf": "MG",
	"email": "dasilvajrjr@gmail.com",
	"senha": "$2a$10$SK/FrINdTZODsMRmGKg2UOkHF9P05pQAmOl3fR8J.K2xxPBbCINoe",
	"role": "ADMIN"
}
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "400", 
            description = "Dados fora das expressões regulares",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 400, com Nome fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Name is required",
		"timestamp": "2025-08-15T15:50:38.312+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com CPF fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "O CPF deve conter exatamente 11 dígitos numéricos.",
		"timestamp": "2025-08-15T15:51:28.035+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com CEP fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "o CEP deve estar no formato 11111111",
		"timestamp": "2025-08-15T15:51:47.026+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com email fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Email no formato inválido",
		"timestamp": "2025-08-15T15:52:04.869+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com CEP inválido",
                        value = """
{
	"status": "BAD_REQUEST",
	"message": "Cep inválido ou inexistente",
	"timestamp": "2025-08-15T15:56:42.628+00:00"
}
                                """
                    ),
                }
                
            )),
        @ApiResponse(
            responseCode = "404", 
            description = "Entidade não encontrada com id: {idPessoa}",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 404",
                        value = """
                            {
	"status": "NOT_FOUND",
	"message": "Entity not found with id: 1",
	"timestamp": "2025-08-15T15:54:10.989+00:00"
}
                                """
                    )
                }
                
            )),
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
                
            ))
    })
    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<AdminPessoaResponseDTO> updateLivro(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da pessoa", required = true)
        @Valid @RequestBody AdminPessoaRequestDTO adminPessoaRequestDTO, 
        @Parameter(description = "ID da pessoa a ser buscada", example = "1")
        @PathVariable Long idPessoa) {

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(adminPessoaRequestDTO, idPessoa));
    }

    //deleta uma pessoa por id
    @Operation(summary = "Deleta uma pessoa por ID")
     @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Pessoa deletada",
            content = @Content(
                mediaType = "text/plain",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = "Pessoa: 8 has been deleted"
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "404", 
            description = "Entidade não encontrada com id: {idLivro}",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 403",
                        value = """
                            {
	"status": "NOT_FOUND",
	"message": "Entity not found with id: 11",
	"timestamp": "2025-08-15T17:46:59.580+00:00"
}
                                """
                    )
                }
                
            )),
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
                
            )),
        @ApiResponse(
            responseCode = "409", 
            description = "Tentativa de deletar pessoa com emprestimo em aberto", 
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 409",
                        value = """
                            {
	"status": "CONFLICT",
	"message": "Impossivel deletar pessoa (id) 4 pois há empréstimos em aberto",
	"timestamp": "2025-08-15T17:36:25.342+00:00"
}
                                """
                    )
                }
                
            ))
    })
    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deleteLivro(
        @Parameter(description = "ID da pessoa a ser buscada", example = "1")
        @PathVariable Long idPessoa) {

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }
}
