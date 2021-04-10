package expression;

import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        ExpressionParser expressionParser = new ExpressionParser();
        TripleExpression expression = expressionParser.parse(scanner.nextLine());
        int t = expression.evaluate(1, 0, 0);
        System.out.println(t);
    }
}
