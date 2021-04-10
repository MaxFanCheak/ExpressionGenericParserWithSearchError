package expression;

import expression.generic.Arithmetic;

public class Divide extends AbstractMathOperation {

    public Divide(AllVariableExpression first, AllVariableExpression second) {
        super(first, second);
    }

    @Override
    public char getMathOperation() {
        return '/';
    }

    @Override
    public int evaluate(int numerator, int denominator) {
        return numerator/denominator;
    }

    @Override
    protected <T extends Number> T apply(Arithmetic<T> arithmetic, T first, T second) {
        return arithmetic.divide(first, second);
    }


}
