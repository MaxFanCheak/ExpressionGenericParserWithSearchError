package expression.generic;

import expression.exceptions.UnsupportedTypeException;

public class DoubleArithmetic implements Arithmetic<Double> {
    public Double parse(String a) {
        try {
            return Double.parseDouble(a);
        } catch (NumberFormatException e) {
            throw new UnsupportedTypeException("Unsupported type");
        }

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
