/*
---
title: Unclosed string literal error
exercises: |
    1. How many double quotes are there?
    2. Is this the correct syntax for Strings?
    3. How would you fix this error?
    4. What does the error look like if you remove the first double quote?
output: |
    code/UnclosedStringLiteralError.java:5: error: unclosed string literal
            String output = "Hello World;
                            ^
    1 error
...
*/

class UnclosedStringLiteralError
{
    public static void main(String[] args)
    {
        String output = "Hello World;
        System.out.println(output);
    }
}