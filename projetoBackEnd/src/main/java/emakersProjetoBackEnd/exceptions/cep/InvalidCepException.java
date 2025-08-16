package emakersProjetoBackEnd.exceptions.cep;

public class InvalidCepException extends RuntimeException{
    public InvalidCepException(){
        super("Cep inválido ou inexistente");
    }
}
