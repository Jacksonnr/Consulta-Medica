package projeto_tomorrow.demo.Exceptions;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String message) {
        super(message);
    }
}
