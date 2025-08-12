package emakersProjetoBackEnd.exceptions.general;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import emakersProjetoBackEnd.exceptions.RestErrorMessage;
import emakersProjetoBackEnd.exceptions.authentication.InvalidLoginException;
import emakersProjetoBackEnd.exceptions.authentication.InvalidRegisterException;
import emakersProjetoBackEnd.exceptions.emprestimo.DevolucaoInvalidaException;
import emakersProjetoBackEnd.exceptions.emprestimo.LivroEmprestadoException;

@ControllerAdvice
public class GeneralExceptionHandler {

    //exceçaõ de entidade não encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> entityNotFoundHandler(EntityNotFoundException exception) {

        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

//exceçaõ das expressões padrão
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<List<RestErrorMessage>> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        List<RestErrorMessage> erros = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> new RestErrorMessage(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }


    //exceçaõ de registro invalido
    @ExceptionHandler(InvalidRegisterException.class)
    private ResponseEntity<RestErrorMessage> invalidRegisterHandler(InvalidRegisterException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    //exceção de login invalido
    @ExceptionHandler(InvalidLoginException.class)
    private ResponseEntity<RestErrorMessage> invalidLoginHandler(InvalidLoginException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    //exceçaõ de livro ja emprestado
    @ExceptionHandler(LivroEmprestadoException.class)
    private ResponseEntity<RestErrorMessage> livroEmprestadoHandler(LivroEmprestadoException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    //exceção de deolução não realizada
    @ExceptionHandler(DevolucaoInvalidaException.class)
    private ResponseEntity<RestErrorMessage> livroEmprestadoHandler(DevolucaoInvalidaException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

}