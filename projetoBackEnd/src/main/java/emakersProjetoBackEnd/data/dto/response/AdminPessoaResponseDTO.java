package emakersProjetoBackEnd.data.dto.response;

import emakersProjetoBackEnd.data.entity.Pessoa;
import emakersProjetoBackEnd.data.entity.Roles;

//informações que só o ADMIN consegue ver
public record AdminPessoaResponseDTO(
    Long id,

    String nome,

    String cpf,

    String cep,

    String logradouro,

    String complemento,

    String bairro,

    String localidade,

    String uf,

    String email,

    String senha,

    Roles role
) {
    public AdminPessoaResponseDTO(Pessoa pessoa){
        this(
            pessoa.getIdPessoa(), 
            pessoa.getName(), 
            pessoa.getCpf(), 
            pessoa.getCep(),
            pessoa.getLogradouro(),
            pessoa.getComplemento(),
            pessoa.getBairro(),
            pessoa.getLocalidade(),
            pessoa.getUf(),
            pessoa.getEmail(), 
            pessoa.getSenha(),
            pessoa.getRole()
            );
    }
}
