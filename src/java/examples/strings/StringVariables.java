/*
---
title: String variables
exercises: |
    1. Make the the program print:
    <br />   
    **Hello Universe<br />
     Hello Universe<br />
    Hello Universe**
    2. How is this different from using literals only?
    3. Change the program to use literals only.
output: |
    Hello World
    Hello World
    Hello World
...
*/

class StringVariables
{
    public static void main(String[] args)
    {
        String outputString = "Hello World";

        System.out.println(outputString);  
        System.out.println(outputString);
        System.out.println(outputString);        
    }
}