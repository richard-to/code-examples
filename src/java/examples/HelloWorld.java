/*
---
title: Hello World
tags: string
exercises:
    - |
        <p>How would you make this program print "<strong>Hello World</strong>" one time instead of two?</p>
    - |
        <p>Make the program print:</p>
        <p><strong>I, for one, welcome our new computer overlords</strong></p>

output: |
    Hello World!
    Hello World!
...
*/

class HelloWorld
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        System.out.println("Hello World!");
    }
}