package javacore1.lambda.calcultor;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(2, 1);
        int c = calc.devide.apply(a, b);
        int d = calc.abs.apply(13);
        calc.println.accept(d);

    }
}
