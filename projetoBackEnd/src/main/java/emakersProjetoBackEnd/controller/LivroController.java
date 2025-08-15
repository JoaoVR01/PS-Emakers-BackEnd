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
import emakersProjetoBackEnd.data.dto.request.LivroRequestDTO;
import emakersProjetoBackEnd.data.dto.response.LivroResponseDTO;
import emakersProjetoBackEnd.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro") //informa que todo endpoint /livro conterá um método que modifica um livro
@Tag(name = "Livros", description = "Endpoints relacionados à gestão de livros.")
public class LivroController {

    @Autowired
    private LivroService livroService; //necessário para acessar os métodos criados no service


    //chamar os método e criar os endpoints

    //retorna todos os livros
    @Operation(summary = "Lista todos os livros disponíveis")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Livros listados com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                            [
	{
		"id": 2,
		"name": "Livro 2",
		"autor": "Autor 1",
		"anoLancamento": "1335-01-20"
	},
	{
		"id": 3,
		"name": "Livro 2",
		"autor": "Autor 1",
		"anoLancamento": "1335-01-20"
	},
	{
		"id": 5,
		"name": "Livro 3",
		"autor": "Autor 1",
		"anoLancamento": "1335-01-20"
	},
	{
		"id": 4,
		"name": "Livro",
		"autor": "Carlos Pereira",
		"anoLancamento": "2010-01-20"
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
    public ResponseEntity<List<LivroResponseDTO>> getAllLivros(){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getallLivros());
    }

    //retorna um livro por id
    @Operation(summary = "Busca um livro por ID")
     @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Livro encontrado",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                        {
	"id": 2,
	"name": "Livro 2",
	"autor": "Autor 1",
	"anoLancamento": "1335-01-20"
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
                        name = "Exemplo códgio 404",
                        value = """
                            {
	"status": "NOT_FOUND",
	"message": "Entity not found with id: 8",
	"timestamp": "2025-08-15T14:48:14.592+00:00"
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
    @GetMapping(value = "/{idLivro}")
    public ResponseEntity<LivroResponseDTO> getLivroById( 
        @Parameter(description = "ID do livro a ser buscado", example = "1") 
        @PathVariable Long idLivro) {

        return ResponseEntity.status(HttpStatus.OK).body(livroService.getLivroById(idLivro));
    }

    //adiciona um novo livro
    @Operation(summary = "Cadastra um novo livro")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "201", 
        description = "Livro criado com sucesso",
        content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 201",
                        value = """
                        {
	"id": 7,
	"name": "Livro 3",
	"autor": "Autor 1",
	"anoLancamento": "1335-01-20"
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
                        name = "Exemplo códgio 400, com nome fora das expressões padrão",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Title is required",
		"timestamp": "2025-08-15T13:01:26.208+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com autor fora das expressões padrão",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Autor is required",
		"timestamp": "2025-08-15T13:01:44.443+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com data fora das expressões padrão",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Eelease date is required",
		"timestamp": "2025-08-15T13:02:01.709+00:00"
	}
]
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
    @PostMapping(value = "/create")
    public ResponseEntity<LivroResponseDTO> createLivro(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do livro", required = true)
        @Valid @RequestBody LivroRequestDTO livroRequestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.createLivro(livroRequestDTO));
    }

    //atualiza os dados de um livro
    @Operation(summary = "Atualiza os dados de um livro existente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Livro atualizado com sucesso",
            content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200",
                        value = """
                        {
	"id": 4,
	"name": "Livro",
	"autor": "Carlos Pereira",
	"anoLancamento": "2010-01-20"
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
                        name = "Exemplo códgio 400, com nome fora das expressões padrão",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Title is required",
		"timestamp": "2025-08-15T13:01:26.208+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com autor fora das expressões padrão",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Autor is required",
		"timestamp": "2025-08-15T13:01:44.443+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com data fora das expressões padrão",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Eelease date is required",
		"timestamp": "2025-08-15T13:02:01.709+00:00"
	}
]
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
                        name = "Exemplo códgio 404, tentando atualizar o livro com id 10, sendo que não consta no banco de dados",
                        value = """
                        {
	"status": "NOT_FOUND",
	"message": "Entity not found with id: 10",
	"timestamp": "2025-08-15T13:06:17.456+00:00"
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
    @PutMapping(value = "/update/{idLivro}")
    public ResponseEntity<LivroResponseDTO> updateLivro(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do livro", required = true)
        @Valid @RequestBody LivroRequestDTO livroRequestDTO,
        @Parameter(description = "ID do livro a ser atualizado", example = "1")     
        @PathVariable Long idLivro){

        return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(livroRequestDTO, idLivro));
    }

    //deleta um livro por id
    @Operation(summary = "Deleta um livro por ID")
     @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Livro Deletado",
            content = @Content(
                mediaType = "text/plain",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 404",
                        value = "Livro 7 has been deleted!"
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
                        name = "Exemplo códgio 404",
                        value = """
                            {
	"status": "NOT_FOUND",
	"message": "Entity not found with id: 19",
	"timestamp": "2025-08-15T13:10:30.606+00:00"
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
                description = "Não é possivel deletar o livro pois ele está emprestado",
                content = @Content(
                mediaType = "aplication/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 409",
                        value = """
                            {
	"status": "CONFLICT",
	"message": "Não é possivel apagar o livro de id: pois está emprestado",
	"timestamp": "2025-08-15T14:43:09.306+00:00"
}
                                """
                    )
                }
                
            ))
    })

    @DeleteMapping(value = "/delete/{idLivro}")
    public ResponseEntity<String> deleteLivro(
    @Parameter(description = "ID do livro a ser deletado", example = "1")    
    @PathVariable Long idLivro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(idLivro));
    }
}
