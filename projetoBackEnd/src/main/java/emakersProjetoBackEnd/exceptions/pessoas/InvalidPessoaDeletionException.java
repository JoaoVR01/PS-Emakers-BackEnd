package emakersProjetoBackEnd.exceptions.pessoas;

public class InvalidPessoaDeletionException extends RuntimeException{

    public InvalidPessoaDeletionException(Long idPessoa){
        super("Impossivel deletar pessoa (id) " + idPessoa + " pois há empréstimos em aberto");
    }
}
