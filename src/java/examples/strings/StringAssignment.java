/*
---
title: String assignment
exercises: |
    1. Make the program print the following using only the outputString variable:
    <br />   
    **Hello Earth<br />
    Hello World<br />
    Hello Universe**
    1. Create a variable named **myString** and assign it to your name.
    <br />   
    **Hello Earth<br />
    Hello World<br />
    Hello Universe**      
output: |
    Hello Earth
    Hello World
    Hello World
...
*/

class StringAssignment
{
    public static void main(String[] args)
    {
        String outputString = "Hello Earth";
        System.out.println(outputString);  

        outputString = "Hello World";
        System.out.println(outputString);  

        System.out.println(outputString);            
    }
}