package emakersProjetoBackEnd.exceptions.emprestimo;

public class LivroEmprestadoException extends RuntimeException{
    public LivroEmprestadoException(String nomeLivro) {
        super("O livro: " + nomeLivro + " já foi emprestado.");
    }
}
