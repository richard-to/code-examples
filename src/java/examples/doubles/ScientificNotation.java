/*
---
title: Scientific notation
exercises: |
    1. What is a benefit of using scientific notation?
    2. Define a variable named **yourScientific** and assign it the value of 92310323.33 using scientific notation.
output: |
    7.0E-5
    7.0E-5
    true
...
*/

public class ScientificNotation
{
    public static void main(String[] args) 
    {
        double scientific = 7.0E-5;
        double normal = 0.00007;
        System.out.println(scientific);
        System.out.println(normal);       

        boolean areEqual;
        areEqual = scientific == normal;
        System.out.println(areEqual);
    }
}