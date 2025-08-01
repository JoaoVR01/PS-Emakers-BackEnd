package emakersProjetoBackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import emakersProjetoBackEnd.data.dto.request.LoginRequestDTO;
import emakersProjetoBackEnd.data.dto.request.PessoaRequestDTO;
import emakersProjetoBackEnd.data.dto.response.LoginResponseDTO;
import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.repository.PessoaRepository;
import emakersProjetoBackEnd.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TokenService tokenService;

    //endpoint de login
    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Pessoa) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid PessoaRequestDTO pessoaRequestDTO){
        if(pessoaRepository.findByEmail(pessoaRequestDTO.email()) != null ){
            return ResponseEntity.badRequest().build();
        }else{
            Pessoa novaPessoa = new Pessoa(pessoaRequestDTO);
            String encryptedPassword = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
            novaPessoa.setSenha(encryptedPassword);
            pessoaRepository.save(novaPessoa);
            return ResponseEntity.ok().build();
        }
    }
}
