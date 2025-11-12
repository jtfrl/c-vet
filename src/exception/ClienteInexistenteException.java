package exception;

public class ClienteInexistenteException extends RuntimeException {
    public ClienteInexistenteException(String message) {
        super(message);
    }
}
