/*
---
title: Cannot find symbol error
exercises: |
    1. What line is the first error on?
    2. What line is the second error on?
    3. What data type is `aNumber`?
    4. Why does Java need to know the data type of a variable?
    5. How can we fix the program?
output: |
    code/CannotFindSymbolError.java:5: error: cannot find symbol
            aNumber = 20;
            ^
      symbol:   variable aNumber
      location: class CannotFindSymbolError
    code/CannotFindSymbolError.java:6: error: cannot find symbol
            System.out.println(aNumber);       
                               ^
      symbol:   variable aNumber
      location: class CannotFindSymbolError
    2 errors
...
*/
public class CannotFindSymbolError 
{
    public static void main(String[] args) 
    {
        aNumber = 20;
        System.out.println(aNumber);       
    }
}