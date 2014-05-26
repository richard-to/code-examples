/*
---
title: Variables are case sensitive
exercises: |
    1. Why does this program not use the **equals** method to test equality?
    2. In other words, why does this program use the **==** operator?
    3. Why is **output** equal to **oUtPuT**?
    4. How can we prove that **output** and **oUtPuT** are indeed different variables?
    5. What are some reasons why naming variables like this is a bad idea?
output: |
    Hello World
    Hello World
    Hello World
    true
    false
    Hello World
    Hello Earth
    false
...
*/
public class CaseSensitive
{
    public static void main(String[] args) 
    {
        String output = "Hello World";
        String oUtPuT = "Hello World";
        String OUTPUT = new String("Hello World");

        System.out.println(output);
        System.out.println(oUtPuT);
        System.out.println(OUTPUT);

        boolean areEqual;
        areEqual = output == oUtPuT;
        System.out.println(areEqual);

        areEqual = output == OUTPUT;
        System.out.println(areEqual);


        oUtPuT = "Hello Earth";
        System.out.println(output);
        System.out.println(oUtPuT);

        areEqual = output == oUtPuT;
        System.out.println(areEqual);
    }
}