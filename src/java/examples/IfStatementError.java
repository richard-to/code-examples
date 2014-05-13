/*
---
title: If Statement - Syntax/Logic Error
exercises:
    - |
        Why does this program output **Hello World!** twice?
    - |
        Make the program print **Hello World** once instead of twice.

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