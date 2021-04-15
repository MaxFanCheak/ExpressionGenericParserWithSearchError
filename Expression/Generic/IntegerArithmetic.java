package expression.generic;

import expression.exceptions.*;

public class IntegerArithmetic implements Arithmetic<Integer>{
    @Override
    public Integer parse(String value) {
        return Integer.valueOf(value);
    }

    public Integer add(Integer a, Integer b) {
        correctAddCheck(a,b);

        return a + b;
    }

    public Integer subtract(Integer a, Integer b) {
        correctSubtractCheck(a,b);

        return a - b;
    }

    public Integer multiply(Integer a, Integer b) {
        correctMultiplyCheck(a,b);

        return a * b;
    }

    public Integer divide(Integer a, Integer b) {
        correctDivideCheck(a,b);

        return a / b;
    }

    public Integer negate(Integer a) {
        if(a == Integer.MIN_VALUE){
            throw new PositiveValueOverFlowException("Happened overflow in moment: " + a);
        }

        return -a;
    }

    public Integer cast(int a) { return a; }

    @Override
    public void DivisionByZeroCheck(Integer a) {
        if (a == 0) {
            throw new DivisionByZeroException("Happened division on zero in moment: " + a + " /  " + a);
        }
    }

    private void correctAddCheck(int a, int b) {
        if (a > 0 && b > 0 && a > Integer.MAX_VALUE - b)
            throw new PositiveValueOverFlowException("Happened overflow in moment: " + a + " + " + b);
        if (a < 0 && b < 0 && a < Integer.MIN_VALUE - b)
            throw new NegativeValueOverFlowException("Happened overflow in moment: " + a + " + " + b);
    }

    private void correctDivideCheck(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new NegativeValueOverFlowException("Happened overflow in moment: " + a + " / " + b);
        }
        DivisionByZeroCheck(b);
    }

    private void correctMultiplyCheck(int a, int b) {
        if (a != 0 && b != 0) {
            if ((a > 0 && b > 0 && a > Integer.MAX_VALUE / b) ||
                    (a < 0 && b < 0 && a < Integer.MAX_VALUE / b) ||
                    (a < 0 && b > 0 && a < Integer.MIN_VALUE / b) || (a > 0 && b < 0 && b < Integer.MIN_VALUE / a)) {
                throw new PositiveValueOverFlowException("Happened overflow in moment: " + a + " * " + b);
            }
        }
    }

    private void correctSubtractCheck(int a, int b) {
        if (b > 0 && Integer.MIN_VALUE + b > a)
            throw new PositiveValueOverFlowException("Happened overflow in moment: " + a + " - " + b);
        if (b < 0 && Integer.MAX_VALUE + b < a)
            throw new NegativeValueOverFlowException("Happened overflow in moment: " + a + " - " + b);
    }
}
