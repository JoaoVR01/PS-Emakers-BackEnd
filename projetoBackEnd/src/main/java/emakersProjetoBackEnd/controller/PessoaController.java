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

import emakersProjetoBackEnd.data.dto.request.PessoaRequestDTO;
import emakersProjetoBackEnd.data.dto.response.PessoaResponseDTO;
import emakersProjetoBackEnd.service.PessoaService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/pessoa") //informa que todo endpoint /livro conterá um método que modifica um livro
public class PessoaController {

    @Autowired
    private PessoaService pessoaService; //necessário para acessar os métodos criados no service

    //chamar os método e criar os endpoints

    @GetMapping(value = "/getall")
    public ResponseEntity<List<PessoaResponseDTO>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoas());
    }

    @GetMapping(value = "/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> getLivroById(@PathVariable Long idPessoa){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaById(idPessoa));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PessoaResponseDTO> createLivro(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaRequestDTO));
    }

    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> updateLivro(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO, @PathVariable Long idPessoa){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(pessoaRequestDTO, idPessoa));
    }

    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long idPessoa){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }
}
