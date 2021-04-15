package expression;


import expression.exceptions.CalculateException;
import expression.generic.Arithmetic;

public class Add<T extends Number> extends AbstractMathOperation<T> {
    public Add(CommonExpression<T> first, CommonExpression<T> second, Arithmetic<T> oper) {
        super(first, second, oper);
    }

    @Override
    protected T evaluate(T a, T b) throws CalculateException {
        return oper.add(a, b);
    }

    @Override
    public char getMathOperation() {
        return '+';
    }
}
