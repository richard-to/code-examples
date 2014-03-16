/*
---
title: For Loop Basic Syntax
tags: loops
exercises:
    - |
        <p>How many times is "<strong>Hello, World</strong>" printed to the console?</p>
    - |
        <p>Make the program print "<strong>Hello, World</strong>" 5 times</p>

output: |
    Hello World!
    Hello World!
    Hello World!
    Hello World!
    Hello World!
    Hello World!
    Hello World!
    Hello World!
    Hello World!
    Hello World!
...
*/

public class ForLoop 
{
    public static void main(String[] args) 
    {
        for (int i = 0; i < 10; i++) 
        {
            System.out.println("Hello, World");
        }
    }
}