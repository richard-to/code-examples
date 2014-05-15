/*
---
title: If Statement - Syntax/Logic Error
exercises: |
    1. Why does this program output **Hello World!** twice?
    2. Make the program print **Hello World** once instead of twice.
output: |
    Hello World
    Hello World
...
*/

public class IfStatementError 
{
    public static void main(String[] args) 
    {
        System.out.println("Hello World");
        if (false);
            System.out.println("Hello World");
    }
}