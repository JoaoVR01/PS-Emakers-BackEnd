package emakersProjetoBackEnd.data.dto.response;

//informações que o usuário consegue ver
import emakersProjetoBackEnd.data.entity.Pessoa;
public record PessoaResponseDTO(
    Long id,

    String nome,

    String email,

    String cpf,

    String cep,

    String logradouro,

    String complemento,

    String bairro,

    String localidade,

    String uf

) {
    public PessoaResponseDTO(Pessoa pessoa){
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
            pessoa.getEmail()

            );
    }
}
