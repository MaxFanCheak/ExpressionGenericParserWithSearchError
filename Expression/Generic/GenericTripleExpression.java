package expression.generic;

import expression.TripleExpression;

public interface GenericTripleExpression<T> extends TripleExpression<T> {
    <T extends Number> T evaluate(Arithmetic<T> arithmetic,T x, T y, T z);

}
