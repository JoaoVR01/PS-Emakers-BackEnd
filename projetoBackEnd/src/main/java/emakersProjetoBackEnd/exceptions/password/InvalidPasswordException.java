package emakersProjetoBackEnd.exceptions.password;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("Senha Incorreta!");
    }
}
