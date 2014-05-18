/*
---
title: Double Precision
exercises: |
    1. Why are **result1** and **result2** not equal?
    2. Find another case where arithmetic does not lead to expected reuslts.
    3. What is a way to check for equality correctly?
output: |
    0.3333333333333333
    0.33333333333333337
    false
...
*/

public class Precision
{
    public static void main(String[] args) 
    {
        double result1 = 1.0/3.0;
        double result2 = 1.0 - 2.0/3.0;
        System.out.println(result1);
        System.out.println(result2);       

        boolean areEqual;
        areEqual = result1 == result2;
        System.out.println(areEqual);
    }
}