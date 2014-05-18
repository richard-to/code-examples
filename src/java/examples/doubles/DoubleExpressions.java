/*
---
title: Double expressions
exercises: |
    1. What do you make of the result of modulus with doubles?
    1. Write an expression divides **20.0** by **3**. How is this result different than with ints?
    2. Write an expression that tests that order of operations apply. 
output: |
    10.7
    7.6
    618.0319999999999
    81.32
    87.8
    3.0999999999999988
...
*/

public class DoubleExpressions
{
    public static void main(String[] args) 
    {
        double sum = 1 + 2.2 + 3 + 4.5;
        System.out.println(sum);   

        double difference = sum - 1 - 2.1;
        System.out.println(difference);

        double product = difference * difference * sum;
        System.out.println(product);

        double quotient = product / difference;
        System.out.println(quotient);

        double expression = (product / difference) + 2.2 - sum + (sum * 1.4);
        System.out.println(expression);

        double modulus = 25.5 % 3.2;
        System.out.println(modulus);
    }
}