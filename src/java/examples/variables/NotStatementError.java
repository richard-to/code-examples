
/*
---
title: Not a statement error
exercises: |
    1. What line is this error on?
    2. What type of errors are thrown?
    3. Is the error messages clear?
    4. How can we fix the program? (Hint: Variable names can only start with A-Z, a-z, $, or _)
output: |
    code/NotStatementError.java:5: error: not a statement
            String 1output = "Hello World";
            ^
    code/NotStatementError.java:5: error: ';' expected
            String 1output = "Hello World";
                  ^
    2 errors
...
*/

public class NotStatementError
{
    public static void main(String[] args) 
    {
        String 1output = "Hello World";
        System.out.println(output);       
    }
}