package emakersProjetoBackEnd.exceptions.emprestimo;

public class DevolucaoInvalidaException extends RuntimeException {
    public DevolucaoInvalidaException(String nomeLivro, String nomePessoa) {
        super("Nenhum livro: " + nomeLivro + " emprestado por: " + nomePessoa);
    }

}
