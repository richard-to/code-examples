/*
---
title: If Statement - Basic Syntax
tags: if statement
exercises: |
    1. Make the program print `Hello Universe` once instead of twice.
output: |
    Hello Universe
    Hello Universe
...
*/

public class IfStatement 
{
    public static void main(String[] args) 
    {
        System.out.println("Hello Universe");
        if (true) 
        {
            System.out.println("Hello Universe");
        }
    }
}