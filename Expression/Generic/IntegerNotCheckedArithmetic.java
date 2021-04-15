package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class IntegerNotCheckedArithmetic implements Arithmetic<Integer> {
    @Override
    public Integer parse(String value) {
        return Integer.valueOf(value);
    }

    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    public Integer divide(Integer a, Integer b) {
        DivisionByZeroCheck(b);

        return a / b;
    }

    public Integer negate(Integer a) {
        return -a;
    }

    @Override
    public Integer cast(int a) {
        return a;
    }

    @Override
    public void DivisionByZeroCheck(Integer a) {
        if (a == 0) {
            throw new DivisionByZeroException("Happened division on zero in moment: " + a + " /  " + a);
        }
    }
}