package expression;

import expression.exceptions.CalculateException;
import java.util.Objects;

public class Variable<T> implements CommonExpression<T> {
    private String var;
    public Variable(String var) {
        this.var=var;
    }

    @Override
    public String toString() {
        return this.var;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        return ((Variable) o).var.equals(this.var);
    }
    public int hashCode() {
        return toString().hashCode();
    }


    @Override
    public T evaluate(T x) throws CalculateException {
        return x;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        if(var.equals("x")) return x;
        if(var.equals("y")) return y;
        else return z;
    }
}
