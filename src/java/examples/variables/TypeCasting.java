/*
---
title: Type casting
exercises: |
    1. Why does **aShort** not equal **100000**?
    2. Declare a variable of type **long** and assign that value to an **int** variable?
output: |
    100000.0
    100000
    100000
    -31072
...
*/
public class TypeCasting
{
    public static void main(String[] args) 
    {
        double aDouble = 100000;
        System.out.println(aDouble);

        long aLong = (long)aDouble;        
        System.out.println(aLong);

        int anInt = (int)aDouble;        
        System.out.println(anInt);

        short aShort = (short)aDouble;
        System.out.println(aShort);
    }
}