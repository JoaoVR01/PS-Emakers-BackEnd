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
        @ApiResponse(responseCode = "200", description = "Livros listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
    })
    @GetMapping(value = "/getall")
    public ResponseEntity<List<LivroResponseDTO>> getAllLivros(){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getallLivros());
    }

    //retorna um livro por id
    @Operation(summary = "Busca um livro por ID")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro encontrado"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idLivro}"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
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
        @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Dados fora das expressões regulares"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
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
        @ApiResponse(responseCode = "201", description = "Livro atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados fora das expressões regulares"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idLivro}"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
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
        @ApiResponse(responseCode = "200", description = "Livro encontrado"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idLivro}"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
    })
    @DeleteMapping(value = "/delete/{idLivro}")
    public ResponseEntity<String> deleteLivro(
    @Parameter(description = "ID do livro a ser deletado", example = "1")    
    @PathVariable Long idLivro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(idLivro));
    }
}
