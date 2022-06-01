package javacore1.lambda.calcultor;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        int c = calc.devide.apply(a, b);
        calc.println.accept(c);
// Вылетает ошибка ArithmeticException, т.к в строке 8 происходит деление на 0, сделал условие -  если одно из слагаемые при делении равно 0 - вернуть 0
    }
}
