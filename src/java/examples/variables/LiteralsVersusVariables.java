/*
---
title: Literals versus variables
exercises: |
    1. Replace **Hello World** with **Hello Universe**. Was it easier using literals or variables?
    2. Replace the literals with a variable.
output: |
    Example using literals:
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World

    Example using variable:
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
...
*/

public class LiteralsVersusVariables 
{
    public static void main(String[] args) 
    {
        System.out.println("Example using literals:");
        
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        
        System.out.println();

        System.out.println("Example using a variable:");

        String output = "Hello World";
        System.out.println(output);
        System.out.println(output);
        System.out.println(output);
        System.out.println(output);
        System.out.println(output);      
    }
}