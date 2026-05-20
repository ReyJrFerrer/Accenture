package org.example;

public class Calculator {
    public int add(int a, int b) {return a + b;}
    public int subtract(int a, int b) {return a - b;}
    public int multiply(int a, int b){return a * b;}
    public int divide(int a, int b) {
        if (b == 0){
            throw new ArithmeticException("Cannot be divide by zero");
        }
        return a/ b;

    }
    public int square(int a){
        return a * a;
    }
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Addition " + calculator.add(2,3));
        System.out.println("Subtraction " + calculator.add(2,3));
        System.out.println("Multiplication" + calculator.add(2,3));
        System.out.println("Division " + calculator.add(2,3));
        System.out.println("Square " + calculator.square(4));
    }
}


