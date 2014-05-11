/*
---
title: Class Name Error
class_name: HelloWorld
tags: compile error could not find or load main class
exercises:
    - |
        <p>Why does this program not compile correctly?</p>
    - |
        <p>Make the program print "<strong>Hello World</strong>"</p>

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