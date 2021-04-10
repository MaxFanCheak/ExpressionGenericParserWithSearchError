package expression.generic;

public class DoubleArithmetic implements Arithmetic<Double> {
    public Double parse(String a) {
        return Double.parseDouble(a);
    }

    public Double add(Double a, Double b) {
        return a + b;
    }

    public Double subtract(Double a, Double b) {
        return a - b;
    }

    public Double multiply(Double a, Double b) {
        return a * b;
    }

    public Double divide(Double a, Double b) {
        return a / b;
    }

    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double cast(int a) {
        return (double)a;
    }

    @Override
    public void DivisionByZeroCheck(Double a) {};
}
