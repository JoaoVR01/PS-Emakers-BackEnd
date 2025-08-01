package emakersProjetoBackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.repository.PessoaRepository;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Pessoa pessoa = pessoaRepository.findByEmail(email);
    
    if (pessoa == null) {
        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    return pessoa; // retorna a entidade que implementa UserDetails
}


}
