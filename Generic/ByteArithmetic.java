package expression.generic;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class ByteArithmetic implements Arithmetic<Byte> {
    public Byte add(Byte a, Byte b) {
        return (byte)(a + b);
    }

    public Byte subtract(Byte a, Byte b) {
        return (byte)(a - b);
    }

    public Byte multiply(Byte a, Byte b) {
        return (byte)(a * b);
    }

    public Byte divide(Byte a, Byte b) {
        DivisionByZeroCheck(b);

        return (byte)(a / b);
    }

    public Byte negate(Byte a) {
        return (byte)-a;
    }

    @Override
    public Byte cast(int a) {
        return (byte)a;
    }

    @Override
    public void DivisionByZeroCheck(Byte a) {
        if (a == 0) {
            throw new DivisionByZeroException("Division by zero!");
        }
    }

    public Byte parse(String a) {
        return Byte.parseByte(a);
    }
}
