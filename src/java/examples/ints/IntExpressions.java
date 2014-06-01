/*
---
title: Int expressions
exercises: |
    1. What is a benefit of using variables in expressions?
    2. Would the value of `difference` be the same if you replaced `sum` with a literal `10`?
    3. Write an expression divides `20` by `3`.Is this the expected result?
    4. Write an expression that tests that order of operations apply.
output: |
    10
    7
    490
    70
    330
...
*/

public class IntExpressions
{
    public static void main(String[] args) 
    {
        int sum = 1 + 2 + 3 + 4;
        System.out.println(sum);   

        int difference = sum - 1 - 2;
        System.out.println(difference);

        int product = difference * difference * sum;
        System.out.println(product);

        int quotient = product / difference;
        System.out.println(quotient);

        int expression = (product / difference) + 20 - sum + (sum * 25);
        System.out.println(expression);
    }
}