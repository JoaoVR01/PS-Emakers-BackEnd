package emakersProjetoBackEnd.data.entity;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import emakersProjetoBackEnd.data.dto.request.AdminPessoaRequestDTO;
import emakersProjetoBackEnd.data.dto.request.PessoaRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Comandos responsáveis por criar os campos e os métodos para acessar as instâncias da classe
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idPessoa")
@Entity
@Table(name = "pessoa")
public class Pessoa implements UserDetails{ //indica que essa classe será usada na autenticação de usuário
    //CRIAÇÃO DOS CAMPOS DA TABELA DO BANCO DE DADOS

    //criação do campo Id de forma que seja atualizado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    //criação do campo nome
    @Column(name = "name",  nullable = false, length = 100) //definir que o campo name será uma coluna da tabela z
    private String name;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "logradouro", length = 100)
    private String logradouro;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "localidade", length = 100)
    private String localidade;

    @Column(name = "uf", length = 100)
    private String uf;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    @Builder
    public Pessoa(PessoaRequestDTO pessoaRequestDTO){
        this.name = pessoaRequestDTO.name();
        this.cpf = pessoaRequestDTO.cpf();
        this.cep = pessoaRequestDTO.cep();
        this.email = pessoaRequestDTO.email();
        this.senha = pessoaRequestDTO.senha();
    }

    @Builder
    public Pessoa(AdminPessoaRequestDTO adminPessoaRequestDTO) {
        this.name = adminPessoaRequestDTO.name();
        this.cpf = adminPessoaRequestDTO.cpf();
        this.cep = adminPessoaRequestDTO.cep();
        this.email = adminPessoaRequestDTO.email();
        this.role = adminPessoaRequestDTO.role();
    }

    //métodos padrão do UserDetails
      @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Roles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    //campo da tabela usado como senha
    public String getPassword() {
        return senha;
    }

    @Override
    //campo da tabela usado como usuario
    public String getUsername() {
        return email;
    }

    //configurações extras
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
