/*
---
title: Double variables
exercises: |
    1. Why do you get an error if you delete line 6?
    2. What happens if you change the value of **aDouble** to **25.5** or **55.2**?
    3. Define a double named **yourDouble** and assign a value to it.
output: |
    -200.5
    25.0
    133.7
    9000.9876
...
*/

public class DoubleVariables 
{
    public static void main(String[] args) 
    {
        double negativeDouble;
        negativeDouble = -200.5;
        System.out.println(negativeDouble);   

        double aDouble = 25.0;
        System.out.println(aDouble);

        double anotherDouble = 133.7;
        System.out.println(anotherDouble);

        double biggerDouble = 9000.9876;
        System.out.println(biggerDouble);         
    }
}