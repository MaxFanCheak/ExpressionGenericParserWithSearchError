package expression.generic;

public class FloatArithmetic implements Arithmetic<Float> {
    @Override
    public Float add(Float a, Float b) {
        return a + b;
    }

    @Override
    public Float subtract(Float a, Float b) {
        return a - b;
    }

    @Override
    public Float multiply(Float a, Float b) {
        return a * b;
    }

    public Float divide(Float a, Float b) {
        return a / b;
    }

    public Float negate(Float a) {
        return -a;
    }

    @Override
    public Float cast(int a) {
        return (float)a;
    }

    @Override
    public void DivisionByZeroCheck(Float a) {}

    public Float parse(String a) {
        return Float.parseFloat(a);
    }
}
