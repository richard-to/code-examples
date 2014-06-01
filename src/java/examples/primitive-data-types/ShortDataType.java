/*
---
title: Short data type
exercises: |
    1. Are the limits of `shorts` greater than `bytes`?
    2. Are the limits of `shorts` greater than `ints`?
    3. What happens if change the value of `aShort` to a value greater than `MAX_VALUE`?
    4. What happens if you change the value of `aShort` to `10.5` or `55.2`?
    5. Define a short named `yourShort` and assign a value to it.
    6. Reassign `yourShort` to a different value.
output: |
    20
    -32768
    32768
...
*/

public class ShortDataType
{
    public static void main(String[] args) 
    {
        short aShort;
        aShort = 20;

        short smallestShort;
        smallestShort = Short.MIN_VALUE;

        short largestShort;
        largestShort = Short.MAX_VALUE;

        System.out.println(aShort);
        System.out.println(smallestShort);
        System.out.println(largestShort);
    }
}