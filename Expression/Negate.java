package expression;


import expression.generic.Arithmetic;

public class Negate implements AllVariableExpression {
    private AllVariableExpression expression;

    public Negate(AllVariableExpression expression) {
        this.expression = expression;
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

    @Override
    public <T extends Number> T evaluate(Arithmetic<T> arithmetic, T x, T y, T z) {
        return arithmetic.cast(Integer.parseInt(expression.toString()));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(expression.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return -x;
    }



}
