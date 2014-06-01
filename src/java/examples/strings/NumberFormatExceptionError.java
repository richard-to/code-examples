/*
---
title: Number format exception error
exercises: |
    1. Why does `parseInt` not work?
    3. What types of String values will throw this type of error?
output: |
    Exception in thread "main" java.lang.NumberFormatException: For input string: "a20"
        at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
        at java.lang.Integer.parseInt(Integer.java:492)
        at java.lang.Integer.parseInt(Integer.java:527)
        at InconvertibleTypeError.main(InconvertibleTypeError.java:7)
...
*/
class NumberFormatExceptionError
{
    public static void main(String[] args)
    {
        int anInt;        
        String aNumber = "a20";
        anInt = Integer.parseInt(aNumber);

        System.out.println(anInt);
    }
}