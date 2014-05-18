/*
---
title: String Equality
exercises: |
    1. Why does **output1** equal **output3** when using the method **equals**, but when using the comparison operator **==**?
    2. Why does **output1** equal **output2** when using the comparison operator in this case?
    3. When comparing literal String values, is it better to use **equals** or **==**?
output: |
    Hello World
    Hello World
    Hello World
    true
    true
    true
    false
...
*/

class StringEquality
{
    public static void main(String[] args)
    {
        String output1 = "Hello World";
        String output2 = "Hello World";
        String output3 = new String("Hello World");

        System.out.println(output1);
        System.out.println(output2);
        System.out.println(output3);
        
        boolean areEqualStrings;

        areEqualStrings = output1.equals(output2);
        System.out.println(areEqualStrings);
        
        areEqualStrings = output1.equals(output3);
        System.out.println(areEqualStrings);
        
        areEqualStrings = output1 == output2;
        System.out.println(areEqualStrings);
        
        areEqualStrings = output1 == output3;
        System.out.println(areEqualStrings);
    }
}