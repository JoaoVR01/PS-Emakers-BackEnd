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
        @ApiResponse(responseCode = "200", description = "Pessoas listadas com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
    })
    @GetMapping(value = "/getall")
    public ResponseEntity<List<AdminPessoaResponseDTO>> getAllPessoas(){

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    //retorna uma pessoa por id
    @Operation(summary = "Busca uma pessoa por ID")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa encontrado"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idPessoa}"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
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
        @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Dados fora das expressões regulares"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
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
        @ApiResponse(responseCode = "201", description = "Pessoa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados fora das expressões regulares"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idPessoa}"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
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
        @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idLivro}"),
        @ApiResponse(responseCode = "403", description = "Token inválido ou cargo sem altorização")
    })
    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deleteLivro(
        @Parameter(description = "ID da pessoa a ser buscada", example = "1")
        @PathVariable Long idPessoa) {

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }
}
