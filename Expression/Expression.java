package expression;

import expression.exceptions.CalculateException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Expression<T> extends ToMiniString {
    T evaluate(T x) throws CalculateException;
}
