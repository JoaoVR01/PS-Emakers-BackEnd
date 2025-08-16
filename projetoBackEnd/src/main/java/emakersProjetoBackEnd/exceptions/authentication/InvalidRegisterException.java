package emakersProjetoBackEnd.exceptions.authentication;

public class InvalidRegisterException extends RuntimeException{
    
    public InvalidRegisterException(String message){
        super(message);
    }
}
