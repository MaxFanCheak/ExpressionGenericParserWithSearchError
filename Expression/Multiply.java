package expression;

import expression.exceptions.CalculateException;
import expression.generic.Arithmetic;

public class Multiply<T extends Number> extends AbstractMathOperation<T> {

    public Multiply(CommonExpression<T> first, CommonExpression<T> second, Arithmetic<T> oper) {
        super(first, second, oper);
    }

    @Override
    public char getMathOperation() {
        return '*';
    }


    @Override
    protected T evaluate(T a, T b) throws CalculateException {
        return oper.multiply(a, b);
    }
}
