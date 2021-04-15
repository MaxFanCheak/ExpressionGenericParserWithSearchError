package expression.parser;

import expression.*;
import expression.generic.Arithmetic;

import java.util.Map;

import static base.Asserts.error;

public class ExpressionParser<T extends Number> extends BaseParser<T> {
    private static final int topLevel = 5;
    private static final int primeLevel = 0;
    private String lastOperator;
    private final Arithmetic<T> oper;
    private static final Map<String, Integer> priorities = Map.of(
            "+", 5,
            "-", 5,
            "*", 4,
            "/", 4,
            "&", 3,
            "^", 2,
            "|", 1,
            ")", 6
    );
    private static final Map<Character, String> firstCharToOperator = Map.of(
            '+', "+",
            '-', "-",
            '*', "*",
            '/', "/",
            ')', ")",
            '&', "&",
            '^', "^",
            '|', "|"
    );

    public ExpressionParser(Arithmetic<T> oper) {
        this.oper = oper;
    }

    @Override
    public CommonExpression<T> parse(String expression) {
        setSource(new StringSource(expression + ")"));
        nextChar();
        skipWhitespaces();
        final CommonExpression<T> genericTripleExpression = parseLevel(topLevel);
        if (ch != '\0') {
            throw error("Unexpected close bracket");
        }
        return genericTripleExpression;
    }

    private CommonExpression<T> parseLevel(int level) {
        if (level == primeLevel) {
            CommonExpression<T> primeExpression = getPrimeExpression();
            skipWhitespaces();
            if (!testOperator()) {
                throw error("Expected operator");
            }
            return primeExpression;
        }
        CommonExpression<T> expression = parseLevel(level - 1);
        while (lastOperator != null && priorities.get(lastOperator) == level) {
            expression = makeExpression(lastOperator, expression, parseLevel(level - 1));
        }
        if (level == topLevel) {
            if (lastOperator == null || !lastOperator.equals(")")) {
                throw error("Expected close bracket");
            }
            lastOperator = null;
        }
        return expression;
    }

    private CommonExpression<T> getPrimeExpression() {
        if (test('(')) {
            return parseLevel(topLevel);
        } else if (test('-')) {
            skipWhitespaces();
            if (between('0', '9')) {
                return getConstExpression(true);
            }else {
                return new Negate<>(getPrimeExpression(), oper);
            }
        } else if (testOperator()) {
            throw error("Unexpected operator");

        } else if (between('0', '9')) {
            return getConstExpression(false);
        } else {
            return getVariableExpression();
        }
    }

    private CommonExpression<T> getVariableExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        while (between('x', 'z')) {
            stringBuilder.append(ch);
            nextChar();
        }
        if (stringBuilder.length() == 0) {
            throw error("Unsupported variable " + ch);
        }
        return new Variable<>(stringBuilder.toString());
    }


    private CommonExpression<T> getConstExpression(boolean isNegative) {
        StringBuilder stringBuilder = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            stringBuilder.append(ch);
            nextChar();
        }
        return new Const<T>(oper.parse(stringBuilder.toString()));
    }

    private boolean testOperator() {
        if (!firstCharToOperator.containsKey(ch)) {
            return false;
        }
        getOperator();
        skipWhitespaces();
        return true;
    }

    private void getOperator() {
        String operator = firstCharToOperator.get(ch);
        expect(operator);
        lastOperator = operator;
    }

    private CommonExpression<T> makeExpression(String operator, CommonExpression<T> a, CommonExpression<T> b) {
        if (operator.equals("+")) {
            return new Add<>(a, b, oper);
        }
        if (operator.equals("-")) {
            return new Subtract<>(a, b, oper);
        }
        if (operator.equals("*")) {
            return new Multiply<>(a, b, oper);
        }
        if (operator.equals("/")) {
            return new Divide<>(a, b, oper);
        }
        throw error("Unsupported operator: " + operator);
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }
}