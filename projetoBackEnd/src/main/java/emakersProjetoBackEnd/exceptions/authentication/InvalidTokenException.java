package emakersProjetoBackEnd.exceptions.authentication;

public class InvalidTokenException extends RuntimeException{
    
    public InvalidTokenException( ){
        super("Token inválido ou não encontrado");
    }
}
