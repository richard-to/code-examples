/*
---
title: Incompatible types error
exercises: |
    1. What line is this error on?
    2. What type of error is thrown?
    3. Do we get an incompatible types error if we assign a double value to `aNumber`?
    4. What about a char?
    5. How can we fix the program if we want `true` to be `1` and `false` to be `0`?
output: |
    code/IncompatibleTypesError.java:6: error: incompatible types
            int aNumber = trueValue;
                          ^
      required: int
      found:    boolean
    1 error
...
*/

public class IncompatibleTypesError 
{
    public static void main(String[] args) 
    {
        boolean trueValue = true;
        int aNumber = trueValue;
        System.out.println(aNumber);       
    }
}