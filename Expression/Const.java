package expression;

import expression.exceptions.CalculateException;

public class Const<T extends Number> implements CommonExpression<T> {
    private final T value;

    public Const(T value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Const<?> c = (Const<?>) o;
        return c.value.equals(value);
    }
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public T evaluate(T x) throws CalculateException {
        return value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }
}

