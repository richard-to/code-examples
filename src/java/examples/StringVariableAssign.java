/*
---
title: Assigning String Variables
tags: string variables assign
exercises:
    - |
        <p>Make the program print the following output using only the outputString variable:</p>
        <br />
        <p><strong>Hello Earth<br />
        Hello World<br />
        Hello Universe</strong></p>
output: |
    Hello Earth
    Hello World
    Hello World
...
*/

class StringVariableAssign
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