package expression.generic;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerArithmetic implements Arithmetic<BigInteger> {
    @Override
    public BigInteger parse(String value) {
        return new BigInteger(value);
    }

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        DivisionByZeroCheck(b);

        return a.divide(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return BigInteger.ZERO.subtract(a);
    }

    @Override
    public BigInteger cast(int a) {
        return BigInteger.valueOf(a);
    }

    @Override
    public void DivisionByZeroCheck(BigInteger a) {
        if (a.equals(new BigInteger(String.valueOf(0)))) {
            throw new DivisionByZeroException("Division on zero!");
        }
    }
}
