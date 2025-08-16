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
import emakersProjetoBackEnd.data.dto.response.PessoaResponseDTO;
import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.data.entity.Roles;
import emakersProjetoBackEnd.exceptions.authentication.InvalidLoginException;
import emakersProjetoBackEnd.exceptions.authentication.InvalidRegisterException;
import emakersProjetoBackEnd.repository.PessoaRepository;
import emakersProjetoBackEnd.service.CepService;
import emakersProjetoBackEnd.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @ApiResponse(
            responseCode = "200", 
            description = "Login efetuado com sucesso",
            content = @Content(
                mediaType = "application/json",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 200, com usuário admin1@gmail.com registrado no banco de dados",
                        value = """
                                { "token": "eyJhbGciOi..." }
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "401", 
            description = "Email ou senha inválidos",
            content = @Content(
                mediaType = "application/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 401, com usuário admin1232@gmail.com não registrado no banco de dados",
                        value = """
                        {
	                        "status": "UNAUTHORIZED",
	                        "message": "Invalid email or password, try again.",
	                        "timestamp": "2025-08-14T21:39:14.354+00:00"
                        }
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 401, com usuário admin1@gmail.com registrado no banco de dados, porem com a senha errada",
                        value = """
                        {
	                        "status": "UNAUTHORIZED",
	                        "message": "Invalid email or password, try again.",
	                        "timestamp": "2025-08-14T21:39:14.354+00:00"
                        }
                                """
                    )
                }
                
            )),
        @ApiResponse(
            responseCode = "400", 
            description = "Email ou senha fora das expressões regulares",
            content = @Content(
                mediaType = "application/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 400, com email fora da expresão regular",
                        value = """
                        [
	                        {
		                        "status": "BAD_REQUEST",
		                        "message": "Email no formato inválido",
		                        "timestamp": "2025-08-14T21:40:40.884+00:00"
	                        }
                        ]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com senha fora da expresão regular",
                        value = """
                        [
	                        {
		                        "status": "BAD_REQUEST",
		                        "message": "Password is required",
		                        "timestamp": "2025-08-14T21:41:32.894+00:00"
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
                mediaType = "application/jason",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 500",
                        value = """
                        
                                """
                    )
                }
                
            ))
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

    //endpoint de registro
    @Operation(summary = "Faz o registro de um usuário")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Registro efetuado com sucesso"
        ),
        @ApiResponse(
            responseCode = "409", 
            description = "Email ou CPF ja encontrado no banco de dados",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PessoaResponseDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 209, usuario com email usuario1@gmail.com já registrado",
                        value = """
                       {
	                        "status": "CONFLICT",
	                        "message": "Email já cadastrado no sistema",
	                        "timestamp": "2025-08-14T21:43:52.768+00:00"
                        }
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 209, usuario com CPF 12345678900 já registrado",
                        value = """
                        {
	                        "status": "CONFLICT",
	                        "message": "CPF já cadastrado no sistema",
	                        "timestamp": "2025-08-14T21:43:52.768+00:00"
                        }
                                """
                    )
                }
                
            )
            ),
        @ApiResponse(
            responseCode = "400", 
            description = "Dados fora das exprssões regulares ou CEP inválido",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PessoaResponseDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 400, com Nome fora da expressão regular",
                        value = """
                        [
	                        {
		                        "status": "BAD_REQUEST",
		                        "message": "Name is required",
		                        "timestamp": "2025-08-14T21:45:18.116+00:00"
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
		"timestamp": "2025-08-15T12:56:08.428+00:00"
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
		"timestamp": "2025-08-15T12:56:48.684+00:00"
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
		"timestamp": "2025-08-15T12:57:26.728+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com senha fora da expressão regular",
                        value = """
                        [
	{
		"status": "BAD_REQUEST",
		"message": "Password is required",
		"timestamp": "2025-08-15T12:58:02.152+00:00"
	}
]
                                """
                    ),

                    @ExampleObject(
                        name = "Exemplo códgio 400, com cep inválido ou inexistente",
                        value = """
{
	"status": "BAD_REQUEST",
	"message": "Cep inválido ou inexistente",
	"timestamp": "2025-08-15T12:59:12.788+00:00"
}
                                """
                    )

                }
                
            )),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = @Content(
                mediaType = "application/json",
                examples = {
                    @ExampleObject(
                        name = "Exemplo códgio 500",
                        value = """
                        
                                """
                    )
                }
                
            ))
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do Usuário", required = true)
        @RequestBody @Valid PessoaRequestDTO pessoaRequestDTO) { 

        if(pessoaRepository.findByEmail(pessoaRequestDTO.email()) != null){
            throw new InvalidRegisterException("Email já cadastrado no sistema");
        }

        if(pessoaRepository.findByCpf(pessoaRequestDTO.cpf()) != null){
            throw new InvalidRegisterException("CPF já cadastrado no sistema");
        }

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
