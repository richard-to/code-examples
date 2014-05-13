/*
---
title: String Variables
tags: string variables
exercises:
    - |
        Create a variable with a value of **Hello Universe** and print the variable.
    - |
        Print the value **Hello Universe** without using a variable.
output: |
    Hello Earth
    Hello World        
...
*/

class StringVariables
{
    public static void main(String[] args)
    {
        String helloString = "Hello Earth";
        System.out.println(helloString);  

        String outputString = "Hello World";
        System.out.println(outputString);            
    }
}