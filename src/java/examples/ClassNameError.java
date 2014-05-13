/*
---
title: Class Name Error
classname: HelloWorld
tags: compile error could not find or load main class
exercises:
    - |
        Why does this program not compile correctly?
    - |
        Make the program print **Hello World**.

output: |
    Error: Could not find or load main class HelloWorld
...
*/

class RandomClassName
{
    public static void main(String[] args)
    {
        System.out.println("Hello World");
    }
}