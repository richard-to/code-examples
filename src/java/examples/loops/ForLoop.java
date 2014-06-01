/*
---
title: For Loop - Basic Syntax
tags: loops
exercises: |
    1. How many times is `Hello Universe` printed to the console?
    2. Make the program print `Hello Universe` 5 times.
output: |
    Hello Universe
    Hello Universe
    Hello Universe
    Hello Universe
    Hello Universe
    Hello Universe
    Hello Universe
    Hello Universe
    Hello Universe
    Hello Universe
...
*/

public class ForLoop 
{
    public static void main(String[] args) 
    {
        for (int i = 0; i < 10; i++) 
        {
            System.out.println("Hello Universe");
        }
    }
}