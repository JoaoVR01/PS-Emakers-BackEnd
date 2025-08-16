package emakersProjetoBackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import emakersProjetoBackEnd.data.dto.request.ChangePasswordRequestDTO;
import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.exceptions.password.EqualOldPasswordException;
import emakersProjetoBackEnd.exceptions.password.InvalidPasswordException;
import emakersProjetoBackEnd.repository.PessoaRepository;

@Service
public class ChangePasswordService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private PessoaRepository pessoaRepository;


    ChangePasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    
    public String changeSenha(ChangePasswordRequestDTO changePasswordRequestDTO) { 
        Pessoa pessoa = pessoaData();

        //verifica se o hash da senha atual digitada é diferente do da senha no banco de dados
        if(!passwordEncoder.matches(changePasswordRequestDTO.senhaAtual(), pessoa.getSenha())) {

            throw new InvalidPasswordException();
        }
        
        //verifica se a nova senha é igual a senha atual
        if(passwordEncoder.matches(changePasswordRequestDTO.newSenha(), pessoa.getSenha())) {
            throw new EqualOldPasswordException();
        }
        else{
            String encryptedPassword = new BCryptPasswordEncoder().encode(changePasswordRequestDTO.newSenha());
            pessoa.setSenha(encryptedPassword);
            pessoaRepository.save(pessoa);
            return "Senha alterada com sucesso";
        }
    }

     //metodo para pegar os dados da pessoa logada
    private Pessoa pessoaData(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return pessoaRepository.findByEmail(email);
    }
}
