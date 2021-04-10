package expression.parser;

import expression.*;
import expression.generic.Arithmetic;
import expression.generic.GenericTripleExpression;

import java.util.Map;

import static base.Asserts.error;

public class ExpressionParser extends BaseParser implements Parser {
    private static final int topLevel = 5;
    private static final int primeLevel = 0;
    private String lastOperator;
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

    @Override
    public AllVariableExpression parse(String expression) {
        setSource(new StringSource(expression + ")"));
        nextChar();
        skipWhitespaces();
        final AllVariableExpression generictripleExpression = parseLevel(topLevel);
        if (ch != '\0') {
            throw error("Unexpected close bracket");
        }
        return generictripleExpression;
    }

    private AllVariableExpression parseLevel(int level) {
        if (level == primeLevel) {
            AllVariableExpression primeExpression = getPrimeExpression();
            skipWhitespaces();
            if (!testOperator()) {
                throw error("Expected operator");
            }
            return primeExpression;
        }
        AllVariableExpression expression = parseLevel(level - 1);
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

    private AllVariableExpression getPrimeExpression() {
        if (test('(')) {
            return parseLevel(topLevel);
        } else if (test('-')) {
            skipWhitespaces();
            if (between('0', '9')) {
                return getConstExpression(true);
            }else {
                return new Negate(getPrimeExpression());
            }
        } else if (testOperator()) {
            throw error("Unexpected operator");

        } else if (between('0', '9')) {
            return getConstExpression(false);
        } else {
            return getVariableExpression();
        }
    }

    private AllVariableExpression getVariableExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        while (between('x', 'z')) {
            stringBuilder.append(ch);
            nextChar();
        }
        if (stringBuilder.length() == 0) {
            throw error("Unsupported variable " + ch);
        }
        return new Variable(stringBuilder.toString());
    }


    private AllVariableExpression getConstExpression(boolean isNegative) {
        StringBuilder stringBuilder = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            stringBuilder.append(ch);
            nextChar();
        }
        return new Const(Integer.parseInt(stringBuilder.toString()));
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

    private AllVariableExpression makeExpression(String operator, AllVariableExpression a, AllVariableExpression b) {
        if (operator.equals("+")) {
            return new Add(a, b);
        }
        if (operator.equals("-")) {
            return new Subtract(a, b);
        }
        if (operator.equals("*")) {
            return new Multiply(a, b);
        }
        if (operator.equals("/")) {
            return new Divide(a, b);
        }
        throw error("Unsupported operator: " + operator);
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }
}