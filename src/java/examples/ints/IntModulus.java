/*
---
title: Int modulus
exercises: |
    1. Calculate the quotient of the expressions and add them to their respective remainders?
    2. What does modulus do?
output: |
    2
    0
    -1
...
*/

public class IntModulus
{
    public static void main(String[] args) 
    {
        int remainder;

        remainder = 8 % 3;
        System.out.println(remainder);

        remainder = 20 % 2;
        System.out.println(remainder);

        remainder = -41 % 5;
        System.out.println(remainder); 
    }
}