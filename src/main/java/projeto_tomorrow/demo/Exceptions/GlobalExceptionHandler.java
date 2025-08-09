package projeto_tomorrow.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> NotFound(NotFoundException e) {
        ErrorMessage error = new ErrorMessage(404, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> EmailAlreadyExists(EmailAlreadyExistsException e) {
        ErrorMessage error = new ErrorMessage(409, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> CpfAlreadyExists(CpfAlreadyExistsException e) {
        ErrorMessage error = new ErrorMessage(409, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> InvalidCpf(PasswordInvalidException e) {
        ErrorMessage error = new ErrorMessage(401, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex){
        String erroMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorMessage error = new ErrorMessage(400, erroMessage, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}