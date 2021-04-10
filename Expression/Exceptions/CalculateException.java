package expression.exceptions;

public abstract class CalculateException extends RuntimeException{
    public CalculateException(String message) {
        super(message);
    }
}
