/*
---
title: Variables names
exercises: |
    1. Variable names can contain these characters: A-Z, a-z, 0-9, $, and _. Define a variable named `$number`.
    2. Variables can't start with a digit. Define a variable named `1number` to see what happens. 
    3. Define a few different variables to see what works and what does not.
output: |
    59
...
*/

public class VariableNames
{
    public static void main(String[] args) 
    {
        int aNumber1 = 39;
        int aNumber2 = 20;
        int sum2Numbers = aNumber1 + aNumber2;

        System.out.println(sum2Numbers);
    }
}