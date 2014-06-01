/*
---
title: More String Equality
exercises: |
    1. Why does `output1` not equal `output2` when using the comparison operator?
    2. Why does `output1` equal `output3` when using the comparison operator?
    3. Why does changing the value of `output3` not effect the value of `output1`?
output: |
    Hello World
    Hello World
    Hello World
    false
    true
    Hello Earth
    Hello World
    false
...
*/

class MoreStringEquality
{
    public static void main(String[] args)
    {
        String output1 = new String("Hello World");
        String output2 = "Hello World";
        String output3 = output1;

        System.out.println(output1);
        System.out.println(output2);
        System.out.println(output3);

        boolean areEqualStrings;

        areEqualStrings = output1 == output2;
        System.out.println(areEqualStrings);

        areEqualStrings = output1 == output3;
        System.out.println(areEqualStrings);

        output3 = "Hello Earth";
        System.out.println(output1);
        System.out.println(output3);

        areEqualStrings = output1 == output3;        
        System.out.println(areEqualStrings);
    }
}