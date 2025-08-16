package emakersProjetoBackEnd.exceptions.authentication;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(){
        super("Invalid email or password, try again.");
    }

}
