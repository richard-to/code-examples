/*
---
title: String Variables
tags: string variables
exercises:
    - |
        <p>Create a variable with a value of "<strong>Hello Universe</strong>" and print the variable.</p>
    - |
        <p>Print the value "<strong>Hello Universe</strong>" without using a variable.</p>
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