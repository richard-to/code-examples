/*
---
title: For Loop - Basic Syntax
tags: loops
exercises:
    - |
        How many times is **Hello World** printed to the console?
    - |
        Make the program print **Hello World** 5 times.

output: |
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
...
*/

public class ForLoop 
{
    public static void main(String[] args) 
    {
        for (int i = 0; i < 10; i++) 
        {
            System.out.println("Hello World");
        }
    }
}