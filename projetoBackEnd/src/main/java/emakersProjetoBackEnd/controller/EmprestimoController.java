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

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/allEmprestimos")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllemprestimos());
    }

    @GetMapping("/allDevoluções")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllDevolucoes(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAlldevolucoes());
    }

    @PostMapping("/emprestar")
    public ResponseEntity<EmprestimoResponseDTO> emprestar(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.emprestar(emprestimoRequestDTO));
    }

    @PostMapping("/devolver")
    public ResponseEntity<EmprestimoResponseDTO> devolver(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.devolver(emprestimoRequestDTO));
    }
}
