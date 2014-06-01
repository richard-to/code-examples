/*
---
title: If Statement - Syntax/Logic Error
exercises: |
    1. Why does this program output `Hello Universe` twice?
    2. Make the program print `Hello Universe` once instead of twice.
output: |
    Hello Universe
    Hello Universe
...
*/

public class IfStatementError 
{
    public static void main(String[] args) 
    {
        System.out.println("Hello Universe");
        if (false);
            System.out.println("Hello Universe");
    }
}