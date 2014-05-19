/*
---
title: Defining variables
exercises: |
    1. Why might it be good to define variable and assign a value at the same time?
    2. What happens if you remove line 5?
    3. Are you allowed to use a variable that is not declared?
    4. Define two String variables and assign values to each.
output: |
    20
    20
...
*/

public class DefiningVariables 
{
    public static void main(String[] args) 
    {
        int aNumber;
        aNumber = 20;
        System.out.println(aNumber);

        int anotherNumber = 20;
        System.out.println(anotherNumber);
    }
}