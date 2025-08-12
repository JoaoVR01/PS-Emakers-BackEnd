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
import emakersProjetoBackEnd.data.dto.response.CepResponseDTO;
import emakersProjetoBackEnd.data.dto.response.LoginResponseDTO;
import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.data.entity.Roles;
import emakersProjetoBackEnd.exceptions.authentication.InvalidLoginException;
import emakersProjetoBackEnd.exceptions.authentication.InvalidRegisterException;
import emakersProjetoBackEnd.repository.PessoaRepository;
import emakersProjetoBackEnd.service.CepService;
import emakersProjetoBackEnd.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Login & Registro", description = "Endpoints relacionados à gestão de logins e registros.")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CepService cepService;

    //endpoint de login
    @PostMapping("/login")
    @Operation(summary = "Faz o login de um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Email ou senha inválidos"),
        @ApiResponse(responseCode = "400", description = "Email ou senha fora das expressões regulares")
    })
    public ResponseEntity<LoginResponseDTO> login(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Email e Senha", required = true)
        @RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((Pessoa) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
            
        }catch(Exception exception){
            throw new InvalidLoginException();
        }
    }

    @Operation(summary = "Faz o registro de um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro efetuado com sucesso"),
        @ApiResponse(responseCode = "409", description = "Email ou CPF ja encontrado no banco de dados"),
        @ApiResponse(responseCode = "400", description = "Dados fora das exprssões regulares")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do Usuário", required = true)
        @RequestBody @Valid PessoaRequestDTO pessoaRequestDTO) { 

        if(pessoaRepository.findByEmail(pessoaRequestDTO.email()) != null || pessoaRepository.findByCpf(pessoaRequestDTO.cpf()) != null){
            throw new InvalidRegisterException();
        }else{
            Pessoa novaPessoa = new Pessoa(pessoaRequestDTO);

            String encryptedPassword = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
            novaPessoa.setSenha(encryptedPassword);

            CepResponseDTO cepResponseDTO = cepService.consultaCep(novaPessoa.getCep());

            novaPessoa.setLogradouro(cepResponseDTO.logradouro());
            novaPessoa.setBairro(cepResponseDTO.bairro());
            novaPessoa.setComplemento(cepResponseDTO.complemento());
            novaPessoa.setUf(cepResponseDTO.uf());
            novaPessoa.setLocalidade(cepResponseDTO.localidade());

            novaPessoa.setRole(Roles.USER);

            pessoaRepository.save(novaPessoa);
            return ResponseEntity.ok().build();
        }
    }
}
