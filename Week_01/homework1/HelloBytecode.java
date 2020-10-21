package com.example.bytecode;

public class HelloBytecode {

    private int a1 = 0;

    public static void main(String[] args) {
        int num1 = 6;
        double num2 = 3;
        System.out.println("addition:" + (num1 + num2));
        System.out.println("subtraction:" + (num1 - num2));
        int num3 = (int) (num1 - num2);
        if (num3 > 0) {
            for (int i = 1; i < num3; i++) {
                System.out.println("division:" + (num1 / i));
            }
        } else {
            System.out.println("multiplication:" + (num1 * num2));
        }
    }
}
