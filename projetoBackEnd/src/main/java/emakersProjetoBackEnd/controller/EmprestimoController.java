package emakersProjetoBackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import emakersProjetoBackEnd.data.dto.request.EmprestimoRequestDTO;
import emakersProjetoBackEnd.data.dto.response.EmprestimoResponseDTO;
import emakersProjetoBackEnd.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/emprestimo")
@Tag(name = "Emprestimos & Devoluções", description = "Endpoints relacionados à gestão de emprestimos e devoluções.")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    //retorna todos os emprestimos ativos
    @Operation(summary = "Lista todos os empréstimos ativos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
        description = "Empréstimos listados com sucesso",
        content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200, com livro do id 1 dentro do banco de dados e disponivel",
                        value = """
                            [
	{
		"idEmprestimo": 2,
		"pessoaResponseDTO": {
			"id": 4,
			"nome": "Usuario1",
			"email": "12325778926",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "user1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 2,
			"name": "Livro 2",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-13",
		"dataDevolucao": null,
		"status": true
	},
	{
		"idEmprestimo": 4,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 1,
			"name": "Livro 1",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-15",
		"dataDevolucao": null,
		"status": true
	},
	{
		"idEmprestimo": 5,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 3,
			"name": "Livro 2",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-15",
		"dataDevolucao": null,
		"status": true
	},
	{
		"idEmprestimo": 6,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 4,
			"name": "Livro 2",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-15",
		"dataDevolucao": null,
		"status": true
	}
]
                                """
                    )
                }
                
            )
        ),
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
    @GetMapping("/allEmprestimos")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllemprestimos());
    }

    //retorna todas as devoluções 
    @Operation(summary = "Lista todas os devoluções realizadas")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Devoluções listadas com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                            [
	{
		"idEmprestimo": 1,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 2,
			"name": "Livro 2",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-13",
		"dataDevolucao": "2025-08-13",
		"status": false
	},
	{
		"idEmprestimo": 3,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 1,
			"name": "Livro 1",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-15",
		"dataDevolucao": "2025-08-15",
		"status": false
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
    @GetMapping("/allDevoluções")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllDevolucoes(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAlldevolucoes());
    }
    
    //empresta um livro
    @Operation(summary = "Empresta um livro para o usuário Logado")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Emprestimo realizado com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200, com livro do id 1 dentro do banco de dados e disponivel",
                        value = """
                            {
	                            "idEmprestimo": 3,
	                            "pessoaResponseDTO": {
		                            "id": 3,
		                            "nome": "ADMIN1",
		                            "email": "12345678900",
		                            "cpf": "35501021",
		                            "cep": "Rua Américo Martins",
		                            "logradouro": "",
		                            "complemento": "Esplanada",
		                            "bairro": "Divinópolis",
	                            	"localidade": "MG",
	                            	"uf": "admin1@gmail.com"
                            	},
	                            "livroResponseDTO": {
	                            	"id": 1,
                            		"name": "Livro 1",
                            		"autor": "Autor 1",
                            		"anoLancamento": "1335-01-20"
                            	},
	                            "dataEmprestimo": "2025-08-15",
                            	"dataDevolucao": null,
                            	"status": true
                            }
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "409", 
            description = "Livro já emprestado",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 409, com livro do id 2 dentro do banco de dados e indisponivel",
                        value = """
                        {
	                        "status": "CONFLICT",
	                        "message": "O livro: Livro 2 já foi emprestado.",
	                        "timestamp": "2025-08-15T12:31:01.373+00:00"
                        }
                                """
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
                        name = "Exemplo códgio 404, com livro do id 10 que não se encontra no banco de dados e indisponivel",
                        value = """
                        {
	                        "status": "NOT_FOUND",
	                        "message": "Entity not found with id: 10",
	                        "timestamp": "2025-08-15T12:31:58.132+00:00"
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
    @PostMapping("/emprestar")
    public ResponseEntity<EmprestimoResponseDTO> emprestar(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da pessoa", required = true)
        @RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.emprestar(emprestimoRequestDTO));
    }

    //devolve um livro
    @Operation(summary = "Devolve um livro emprestado ao usuário Logado")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Devolução realizada com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200, com livro do id 1 se encontra no banco de dados e foi emprestado pelo usuário",
                        value = """
                        {
	"idEmprestimo": 3,
	"pessoaResponseDTO": {
		"id": 3,
		"nome": "ADMIN1",
		"email": "12345678900",
		"cpf": "35501021",
		"cep": "Rua Américo Martins",
		"logradouro": "",
		"complemento": "Esplanada",
		"bairro": "Divinópolis",
		"localidade": "MG",
		"uf": "admin1@gmail.com"
	},
	"livroResponseDTO": {
		"id": 1,
		"name": "Livro 1",
		"autor": "Autor 1",
		"anoLancamento": "1335-01-20"
	},
	"dataEmprestimo": "2025-08-15",
	"dataDevolucao": "2025-08-15",
	"status": false
}
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "400", 
            description = "Nenhum emprestimo do livro feito pelo usuario",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 400, com livro do id 10 se encontra no banco de dados porém não foi emprestado pelo usuário",
                        value = """
                       {
	"status": "BAD_REQUEST",
	"message": "Nenhum livro: Livro 3 emprestado por: admin1@gmail.com",
	"timestamp": "2025-08-15T12:42:05.104+00:00"
}
                                """
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
                        name = "Exemplo códgio 404, livro com id 10 não foi encontrado no banco de dados",
                        value = """
                       {
	"status": "NOT_FOUND",
	"message": "Entity not found with id: 10",
	"timestamp": "2025-08-15T12:42:37.701+00:00"
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
    @PostMapping("/devolver")
    public ResponseEntity<EmprestimoResponseDTO> devolver(@Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.devolver(emprestimoRequestDTO));
    }

    //mostra os emprestimos somente do usuario
    @Operation(summary = "Lista todos os emprestimos do usuario logado")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Empréstimos listados com sucesso",
             content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                        [
	{
		"idEmprestimo": 4,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 1,
			"name": "Livro 1",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-15",
		"dataDevolucao": null,
		"status": true
	},
	{
		"idEmprestimo": 5,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 3,
			"name": "Livro 2",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-15",
		"dataDevolucao": null,
		"status": true
	},
	{
		"idEmprestimo": 6,
		"pessoaResponseDTO": {
			"id": 3,
			"nome": "ADMIN1",
			"email": "12345678900",
			"cpf": "35501021",
			"cep": "Rua Américo Martins",
			"logradouro": "",
			"complemento": "Esplanada",
			"bairro": "Divinópolis",
			"localidade": "MG",
			"uf": "admin1@gmail.com"
		},
		"livroResponseDTO": {
			"id": 4,
			"name": "Livro 2",
			"autor": "Autor 1",
			"anoLancamento": "1335-01-20"
		},
		"dataEmprestimo": "2025-08-15",
		"dataDevolucao": null,
		"status": true
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
    @GetMapping("/privateEmprestimos")
    public ResponseEntity<List<EmprestimoResponseDTO>> privateGetAllEmpréstimos() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.privateGetAllEmprestimos());
    }
}
