package expression;

import expression.generic.Arithmetic;

public class Subtract extends AbstractMathOperation {
    public Subtract(AllVariableExpression first, AllVariableExpression second) {
        super(first, second);
    }

    @Override
    public char getMathOperation() {
        return '-';
    }

    @Override
    public int evaluate(int minuend, int subtrahend) {
        return minuend-subtrahend;
    }

    @Override
    protected <T extends Number> T apply(Arithmetic<T> arithmetic, T first, T second) {
        return arithmetic.subtract(first,second);
    }


}
