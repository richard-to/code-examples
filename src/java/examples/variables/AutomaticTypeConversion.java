/*
---
title: Automatic type conversion
exercises: |
    1. Why does this program not not throw an error since we are assigning to different data types?
    2. Why is it an error if you assign the value of **aLong** to **aByte**? Isn't aLong equal to 1?
output: |
    1.0
...
*/
public class AutomaticTypeConversion
{
    public static void main(String[] args) 
    {
        byte aByte = 1;
        short aShort = aByte;
        int anInt = aShort;
        long aLong = anInt;
        float aFloat = aLong;
        double aDouble = aFloat;

        System.out.println(aDouble);              
    }
}