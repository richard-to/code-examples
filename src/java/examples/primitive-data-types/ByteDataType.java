/*
---
title: Byte data type
exercises: |
    1. Are the limits of `bytes` greater than `ints`?
    2. What happens if change the value of `aByte` to a value smaller than `MIN_VALUE`?
    3. What happens if you change the value of `aByte` to `10.5` or `55.2`?
    4. Define a byte named `yourByte` and assign a value to it.
    5. Reassign `yourByte` to a different value.
output: |
    20
    -128
    127
...
*/

public class ByteDataType
{
    public static void main(String[] args) 
    {
        byte aByte;
        aByte = 20;

        byte smallestByte;
        smallestByte = Byte.MIN_VALUE;

        byte largestByte;
        largestByte = Byte.MAX_VALUE;

        System.out.println(aByte);
        System.out.println(smallestByte);
        System.out.println(largestByte);
    }
}