package expression.generic;

import expression.exceptions.*;
import expression.parser.ExpressionParser;

import java.math.BigInteger;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        switch (mode) {
            case "i":
                return resToTable(new IntegerArithmetic(), expression, x1, x2, y1, y2, z1, z2);
            case "d":
                return resToTable(new DoubleArithmetic(), expression, x1, x2, y1, y2, z1, z2);
            case "bi":
                return resToTable(new BigIntegerArithmetic(), expression, x1, x2, y1, y2, z1, z2);
            case "f" :
                return resToTable(new FloatArithmetic(), expression, x1, x2, y1, y2, z1, z2);
            case "b" :
                return resToTable(new ByteArithmetic(), expression, x1, x2, y1, y2, z1, z2);
            case "u" :
                return resToTable(new IntegerNotCheckedArithmetic(), expression, x1, x2, y1, y2, z1, z2);
            default:
                throw new UnsupportedTypeException(mode);
        }
    }
    private <T extends Number> Object[][][] resToTable(Arithmetic<T> arithmetic, String expressionOnString, int x1, int x2, int y1, int y2, int z1, int z2) {
        GenericTripleExpression expression = new ExpressionParser().parse(expressionOnString);
        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = 0; x <= x2 - x1; x++) {
            for (int y = 0; y <= y2 - y1; y++) {
                for (int z = 0; z <= z2 - z1; z++) {
                    try {
                        T first = arithmetic.cast(x + x1);
                        T second = arithmetic.cast(y + y1);
                        T third = arithmetic.cast(z + z1);
                        table[x][y][z] = expression.evaluate(arithmetic, first, second, third);
                    } catch (CalculateException e) {
                        table[x][y][z] = null;
                        continue;
                    }
                }
            }
        }
        return table;
    }
}