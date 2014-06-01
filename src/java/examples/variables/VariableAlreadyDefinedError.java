
/*
---
title: Variable already defined error
exercises: |
    1. What line is this error on?
    2. What does the error message mean by `already defined`?
    3. How are variables defined? Can two variables have the same name?
output: |
    code/VariableAlreadyDefinedError.java:6: error: variable output is already defined in method main(String[])
            String output = "Hello World";
                   ^
    1 error
...
*/

public class VariableAlreadyDefinedError
{
    public static void main(String[] args) 
    {
        String output;
        String output = "Hello World";
        System.out.println(output);       
    }
}