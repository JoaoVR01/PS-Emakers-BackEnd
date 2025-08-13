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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/emprestimo")
@Tag(name = "Emprestimos & Devoluções", description = "Endpoints relacionados à gestão de emprestimos e devoluções.")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    //retorna todos os emprestimos ativos
    @Operation(summary = "Lista todos os empréstimos ativos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empréstimos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/allEmprestimos")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllemprestimos());
    }

    //retorna todas as devoluções 
    @Operation(summary = "Lista todas os devoluções realizadas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devoluções listadas com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/allDevoluções")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllDevolucoes(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAlldevolucoes());
    }
    
    //empresta um livro
    @Operation(summary = "Empresta um livro para o usuário Logado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Emprestimo realizado com sucesso"),
        @ApiResponse(responseCode = "409", description = "Livro emprestado"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idLivro}")
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
        @ApiResponse(responseCode = "200", description = "Emprestimo realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Nenhum emprestimo do livro feito pelo usuario"),
        @ApiResponse(responseCode = "404", description = "Entidade não encontrada com id: {idLivro}")
    })
    @PostMapping("/devolver")
    public ResponseEntity<EmprestimoResponseDTO> devolver(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.devolver(emprestimoRequestDTO));
    }

    @Operation(summary = "Lista todos os emprestimos do usuario logado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empréstimos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/privateEmprestimos")
    public ResponseEntity<List<EmprestimoResponseDTO>> privateGetAllEmpréstimos() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.privateGetAllEmprestimos());
    }
}
