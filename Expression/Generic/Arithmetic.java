package expression.generic;

public interface Arithmetic<T extends Number> {
    T parse(String value);

    public T add(T a, T b);

    public T subtract(T a, T b);

    public T multiply(T a, T b);

    public T divide(T a, T b);

    public T negate(T a);

    public T cast(int a);

    public void DivisionByZeroCheck(T a);

}
