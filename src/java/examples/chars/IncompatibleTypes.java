/*
---
title: Incompatible types
exercises: |
    1. What type of error is thrown?
    2. Why does the error message say `found: String`?
    3. Does it make sense that `aChar` requires a char variable?    
    4. How can we fix the program?
output: |
    code/IncompatibleTypes.java:5: error: incompatible types
            char aChar = "a";
                         ^
      required: char
      found:    String
    1 error
...
*/

public class IncompatibleTypes 
{
    public static void main(String[] args) 
    {
        char aChar = "a";
        System.out.println(aChar);       
    }
}