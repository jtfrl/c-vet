package exception;
import java.io.InvalidClassException;

public class EstoqueInsuficienteException extends Exception{
    public EstoqueInsuficienteException(String msg){
        super(msg);
    }

    public static void validaQtd(int etq, Integer real_etq) throws EstoqueInsuficienteException{

        if(etq>real_etq){
            throw new EstoqueInsuficienteException("O estoque é insuficiente\n.");
        }

    }

}