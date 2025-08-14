package emakersProjetoBackEnd.exceptions.password;

public class EqualOldPasswordException extends RuntimeException {
    public EqualOldPasswordException() {
        super("A nova senha não pode ser igual a atual");
    }
}
