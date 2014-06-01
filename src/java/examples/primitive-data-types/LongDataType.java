/*
---
title: Long data type
exercises: |
    1. What is the order of bytes, longs, ints, and shorts in terms of max values?
    3. What happens if change the value of `aLong` to a value greater than `MAX_VALUE`?
    4. What happens if you change the value of `aLong` to `120.5` or `-515.2`?
    5. Define a long named `yourLong` and assign a value to it.
    6. Reassign `yourLong` to a different value.
output: |
    2323323
    -9223372036854775808
    9223372036854775807
...
*/

public class LongDataType
{
    public static void main(String[] args) 
    {
        long aLong;
        aLong = 2323323;

        long smallestLong;
        smallestLong = Long.MIN_VALUE;

        long largestLong;
        largestLong = Long.MAX_VALUE;

        System.out.println(aLong);
        System.out.println(smallestLong);
        System.out.println(largestLong);
    }
}