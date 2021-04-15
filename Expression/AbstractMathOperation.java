package expression;

import expression.exceptions.CalculateException;
import expression.generic.Arithmetic;

public abstract class AbstractMathOperation<T extends Number> implements CommonExpression<T>, MathOperationExpression {
    protected final CommonExpression<T> first;
    protected final CommonExpression<T> second;
    protected final Arithmetic<T> oper;

    public AbstractMathOperation(CommonExpression<T> first, CommonExpression<T> second, Arithmetic<T> oper) {
        this.first = first;
        this.second = second;
        this.oper = oper;
    }

    protected abstract T evaluate(T a, T b) throws CalculateException;

    public T evaluate(T var) throws CalculateException {
        return evaluate(first.evaluate(var), second.evaluate(var));
    }

    public T evaluate(T x, T y, T z) throws CalculateException {
        return evaluate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

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
        AbstractMathOperation<T> bo = (AbstractMathOperation<T>) o;
        return bo.first.equals(this.first) && bo.second.equals(this.second);
    }

    public int hashCode() {
        return 13 * first.hashCode() + 17 * second.hashCode() + 19 * getClass().hashCode();
    }
}
