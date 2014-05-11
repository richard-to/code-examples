/*
---
title: If Statement - Basic Syntax
tags: if statement
exercises:
    - |
        <p>Make the program print "<strong>Hello World</strong>" once instead of twice.</p>

output: |
    Hello World
    Hello World
...
*/

public class IfStatement 
{
    public static void main(String[] args) 
    {
        System.out.println("Hello World");
        if (true) 
        {
            System.out.println("Hello World");
        }
    }
}