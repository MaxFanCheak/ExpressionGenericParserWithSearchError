package expression;

import expression.generic.Arithmetic;

public class Variable implements AllVariableExpression {
    private String var;
    public Variable(String var) {
        this.var=var;
    }
    @Override
    public int evaluate(int x) {
        return evaluate(x,0,0);
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
    public <T extends Number> T evaluate(Arithmetic<T> arithmetic, T x, T y, T z) {
        if(var.equals("x")) return x;
        if(var.equals("y")) return y;
        else return z;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if(var.equals("x")) return x;
        if(var.equals("y")) return y;
        else return z;
    }
}
