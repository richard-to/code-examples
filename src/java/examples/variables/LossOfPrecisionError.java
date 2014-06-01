/*
---
title: Possible loss of precision
exercises: |
    1. What line is this error on?
    2. What data type can we use to avoid this error in this case?
    3. Can you make this program throw a loss of precision error for a `long`?
output: |
    code/LossOfPrecisionError.java:6: error: possible loss of precision
            aByte = 55334;
                    ^
      required: byte
      found:    int
    1 error
...
*/

public class LossOfPrecisionError
{
    public static void main(String[] args) 
    {
        byte aByte;
        aByte = 55334;

        System.out.println(aByte);
    }
}