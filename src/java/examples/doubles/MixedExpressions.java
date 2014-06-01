/*
---
title: Mixed expressions
exercises: |
    1. Change the datatype of `expression1` to an `int`. Why is this an error?
    2. Calculate `expression2` with a calculator. Does your result differ?
    3. Why is the result of `expression3` different from `expression2`?
    4. Modify `expression2` so it still results in the value `86.0`.  
    5. Modify `expression2` so it results in the value `85.0`.
output: |
    55.5
    86.0
    85.0
...
*/

public class MixedExpressions
{
    public static void main(String[] args) 
    {
        double a = 25.5;
        int b = 30;
        double expression1 = a + b;
        System.out.println(expression1);

        double expression2 = (20 + 30 - b / 4) * 2.0;
        System.out.println(expression2);

        double expression3 = (20 + 30 - b / 4.0) * 2.0;
        System.out.println(expression3);
    }
}