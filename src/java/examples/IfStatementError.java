/*
---
title: If Statement With Syntax Error
exercises:
    - |
        <p>Why does this program output "<strong>Hello, World!</strong>" twice?</p>
    - |
        <p>Make the program print "<strong>Hello, World</strong>" once instead of twice.</p>

output: |
    Hello World!
    Hello World!
...
*/

public class IfStatementError 
{
    public static void main(String[] args) 
    {
        System.out.println("Hello, World");
        if (false);
            System.out.println("Hello, World");
    }
}