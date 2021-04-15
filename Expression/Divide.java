package expression;

import expression.exceptions.CalculateException;
import expression.generic.Arithmetic;

public class Divide<T extends Number> extends AbstractMathOperation<T> {


    public Divide(CommonExpression<T> first, CommonExpression<T> second, Arithmetic<T> oper) {
        super(first, second, oper);
    }

    @Override
    public char getMathOperation() {
        return '/';
    }


    @Override
    protected T evaluate(T a, T b) throws CalculateException {
        return oper.divide(a, b);
    }
}
