package emakersProjetoBackEnd.exceptions.livro;

public class InvalidLivroDeletionException extends RuntimeException {
    public InvalidLivroDeletionException(Long idLivro){
        super("Não é possivel apagar o livro de id: " + idLivro + "pois está emprestado");
    }
}
