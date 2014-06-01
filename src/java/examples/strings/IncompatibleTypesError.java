/*
---
title: Incompatible types error
exercises: |
    1. What type of error is thrown?
    2. Why does the error message say `found: char`?
    3. Does it make sense that `output` requires a String?    
    4. How can we fix the program?
output: |
    code/IncompatibleTypesError.java:5: error: incompatible types
            String output = 'a';
                            ^
      required: String
      found:    char
    1 error
...
*/

public class IncompatibleTypesError
{
    public static void main(String[] args) 
    {
        String output = 'a';
        System.out.println(output);       
    }
}