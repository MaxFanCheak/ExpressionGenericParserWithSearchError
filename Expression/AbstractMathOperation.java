package expression;

import expression.generic.Arithmetic;

public abstract class AbstractMathOperation implements AllVariableExpression, MathOperationExpression {
    private AllVariableExpression first;
    private AllVariableExpression second;

    public AbstractMathOperation(AllVariableExpression first, AllVariableExpression second) {
        this.first = first;
        this.second = second;
    }

    public int evaluate(int x) {
        return evaluate(first.evaluate(x), second.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return evaluate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    protected abstract int evaluate(int x, int y);

    @Override
    public <T extends Number> T evaluate(Arithmetic<T> arithmetic, T x, T y, T z) {
        return apply(arithmetic, first.evaluate(arithmetic, x, y, z), second.evaluate(arithmetic, x, y, z));
    }

    protected abstract <T extends Number> T apply(Arithmetic<T> arithmetic, T first, T second);


    @Override
    public String toString() {
        return "(" + first + " " + getMathOperation() + " " + second + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        AbstractMathOperation bo = (AbstractMathOperation) o;
        return bo.first.equals(this.first) && bo.second.equals(this.second);
    }

    public int hashCode() {
        return 13 * first.hashCode() + 17 * second.hashCode() + 19 * getClass().hashCode();
    }
}
