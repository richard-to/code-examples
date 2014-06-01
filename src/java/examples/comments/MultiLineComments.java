/*
---
title: Multi-line comments
exercises: |
    1. What does the method `aMysteryMethod` do?
    2. Make the program print: 

            Hello World
            Hello Earth
            Hello Universe
        
    3. Change this program to use single line comments.
    4. What are some benefits of multi-line comments?
output: |
    Hello World
...
*/

class MultiLineComments
{
    public static void main(String[] args)
    {
        aMysteryMethod("World");

        /*
        aMysteryMethod("Earth");
        aMysteryMethod("Universe");
        */
    }

    /* 
    This method prints a greeting to the console using
    the specified name.

    You can pass in a String to the method to change 
    the name of the person you want to greet.
    */
    public static void aMysteryMethod(String name)
    {
        System.out.println("Hello " + name);
    }
}