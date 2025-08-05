package emakersProjetoBackEnd.exceptions.authentication;

public class InvalidRegisterException extends RuntimeException{
    
    public InvalidRegisterException( ){
        super("Already in the DataBase");
    }
}
