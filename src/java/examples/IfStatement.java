/*
---
title: If Statement - Basic Syntax
tags: if statement
exercises:
    - |
        Make the program print **Hello World** once instead of twice.

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