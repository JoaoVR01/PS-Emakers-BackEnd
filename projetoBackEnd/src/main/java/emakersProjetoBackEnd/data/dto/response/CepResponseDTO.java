package emakersProjetoBackEnd.data.dto.response;


public record CepResponseDTO(
    String cep,

    String logradouro,

    String complemento,

    String bairro,

    String localidade,

    String uf,

    boolean erro
) {

}
