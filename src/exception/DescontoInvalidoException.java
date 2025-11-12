package exception;

public class DescontoInvalidoException extends Exception{
    public DescontoInvalidoException(String msg){
        super(msg);
    }
    public static void validaPercentual(double p) throws DescontoInvalidoException {
        if(p<0){
            throw new DescontoInvalidoException("O percentual negativo é inválido\n.");
        }
        if(p>0.95){
            throw new DescontoInvalidoException("Valor de desconto está acima do limite máximo\n.");
        }
    }
}
