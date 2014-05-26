/*
---
title: Type conversion with strings
exercises: |
    1. Why is **output1** and **output2** different? What does this say about type conversion with Strings?
    2. Does this also happen for booleans, doubles and floats?
    3. What happens if you change **output1** to type **int** instead of **String**?
output: |
    Hello World 1020
    Hello World 30
...
*/
public class TypeConversionWithStrings
{
    public static void main(String[] args) 
    {
        String output1 = "Hello World " + 10 + 20;
        System.out.println(output1);

        String output2 = "Hello World " + (10 + 20);
        System.out.println(output2);               
    }
}