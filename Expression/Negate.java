package expression;


import expression.exceptions.CalculateException;
import expression.generic.Arithmetic;

public class Negate<T extends Number> implements CommonExpression<T> {
    private CommonExpression<T> expression;
    private Arithmetic<T> oper;

    public Negate(CommonExpression<T> expression, Arithmetic<T> oper) {
        this.expression = expression;
        this.oper = oper;
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        boolean hasBrackets = expression instanceof AbstractMathOperation;
        return "-" + (hasBrackets ? "(" : "") + expression.toMiniString() + (hasBrackets ? ")" : "");
    }

    public T evaluate(T var) throws CalculateException {
        return oper.negate(expression.evaluate(var));
    }

    public T evaluate(T x, T y, T z) throws CalculateException {
        return oper.negate(expression.evaluate(x, y, z));
    }
}
