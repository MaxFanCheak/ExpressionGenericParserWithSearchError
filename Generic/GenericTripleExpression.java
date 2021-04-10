package expression.generic;

import expression.TripleExpression;

public interface GenericTripleExpression extends TripleExpression {
    <T extends Number> T evaluate(Arithmetic<T> arithmetic,T x, T y, T z);

    @Override
    default int evaluate(int x, int y, int z){
        return evaluate(new IntegerNotCheckedArithmetic(), x, y, z);
    }

}
