/*
---
title: Int limits
exercises: |
    1. What happens if you add 1 to `maxInteger`?
    2. What happens if you subtract 1 from `minInteger`?
    3. How can this behavior be problematic?
    4. Does Java have data type that can handle larger integers?
    5. Why might it be useful to use a data type with limited size?
    6. What happens if you assign the value `2147483648` to `maxInteger`?
    6. What happens if you assign the value `-2147483649` to `minInteger`?
output: |
    2147483647
    -2147483648
...
*/

public class IntLimits 
{
    public static void main(String[] args) 
    {
        int maxInteger = Integer.MAX_VALUE;
        int minInteger = Integer.MIN_VALUE;

        System.out.println(maxInteger);   
        System.out.println(minInteger);         
    }
}