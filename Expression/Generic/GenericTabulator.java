package expression.generic;

import expression.CommonExpression;
import expression.exceptions.*;
import expression.parser.ExpressionParser;
import java.util.Map;

public class GenericTabulator implements Tabulator {
    private static final Map<String, Arithmetic<?>> arithmeticMode = Map.of(
            "d",    new DoubleArithmetic(),
            "f",    new FloatArithmetic(),
            "b",    new ByteArithmetic(),
            "i",    new IntegerArithmetic(),
            "u",    new IntegerNotCheckedArithmetic(),
            "bi",   new BigIntegerArithmetic()

    );
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        if (arithmeticMode.containsKey(mode)) {
            return resToTable(arithmeticMode.get(mode), expression, x1, x2, y1, y2, z1, z2);
        }
        throw new UnsupportedTypeException(mode);
    }
    private <T extends Number> Object[][][] resToTable(Arithmetic<T> arithmetic, String expressionOnString, int x1, int x2, int y1, int y2, int z1, int z2) {
        CommonExpression<T> expression = new ExpressionParser<>(arithmetic).parse(expressionOnString);
        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = 0; x <= x2 - x1; x++) {
            for (int y = 0; y <= y2 - y1; y++) {
                for (int z = 0; z <= z2 - z1; z++) {
                    try {
                        T first = arithmetic.cast(x + x1);
                        T second = arithmetic.cast(y + y1);
                        T third = arithmetic.cast(z + z1);
                        table[x][y][z] = expression.evaluate(first, second, third);
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