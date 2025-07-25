package emakersProjetoBackEnd.data.dto.response;

import emakersProjetoBackEnd.data.entity.Pessoa;

public record PessoaResponseDTO(
    Long id,

    String nome,

    String cpf,

    String cep,

    String email,

    String senha
) {
    public PessoaResponseDTO(Pessoa pessoa){
        this(pessoa.getIdPessoa(), pessoa.getName(), pessoa.getCpf(), pessoa.getCep(), pessoa.getEmail(), pessoa.getSenha());
    }
}
