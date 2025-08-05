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

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessage> entityNotFoundHandler(EntityNotFoundException exception) {

        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<List<RestErrorMessage>> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        List<RestErrorMessage> erros = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> new RestErrorMessage(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(InvalidRegisterException.class)
    private ResponseEntity<RestErrorMessage> invalidRegisterHandler(InvalidRegisterException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    @ExceptionHandler(InvalidLoginException.class)
    private ResponseEntity<RestErrorMessage> invalidLoginHandler(InvalidLoginException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    @ExceptionHandler(LivroEmprestadoException.class)
    private ResponseEntity<RestErrorMessage> livroEmprestadoHandler(LivroEmprestadoException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }

    @ExceptionHandler(DevolucaoInvalidaException.class)
    private ResponseEntity<RestErrorMessage> livroEmprestadoHandler(DevolucaoInvalidaException exception) {
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(errorMessage.status()).body(errorMessage);
    }
    
}