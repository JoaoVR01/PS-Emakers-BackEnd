package emakersProjetoBackEnd.data.dto.response;

import emakersProjetoBackEnd.data.entity.Pessoa;

public record PessoaResponseDTO(
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

    String senha
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
            pessoa.getEmail(), 
            pessoa.getSenha()
            );
    }
}
