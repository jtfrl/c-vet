package exception;

public class AnimalInexistenteException extends RuntimeException {
    public AnimalInexistenteException(String message) {
        super(message);
    }
}
