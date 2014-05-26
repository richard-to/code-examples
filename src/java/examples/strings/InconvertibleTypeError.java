/*
---
title: Inconvertible type error
exercises: |
    1. How would you fix this error?
    2. Why does type casting not work?
output: |
    code/InconvertibleTypeError.java:7: error: inconvertible types
            anInt = (int)aNumber;
                         ^
      required: int
      found:    String
    1 error
...
*/
class InconvertibleTypeError
{
    public static void main(String[] args)
    {
        int anInt;        
        String aNumber = "20";
        anInt = (int)aNumber;

        System.out.println(anInt);
    }
}