package expression.exceptions;

public class NegativeValueOverFlowException extends CalculateException {
    public NegativeValueOverFlowException(String message) {
        super(message);
    }
}
