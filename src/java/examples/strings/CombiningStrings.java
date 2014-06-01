/*
---
title: Combining Strings
exercises: |
    1. Make the program print `Hello Earth` using the variable `earth`.
    2. Make the program print `Hello Earth and World`.
    3. What are some cases where combining strings can be useful?
output: |
    Hello World
...
*/

class CombiningStrings
{
    public static void main(String[] args)
    {
        String hello = "Hello";
        String space = " ";
        String world = "World";

        String helloWorld = hello + space + world;
        System.out.println(helloWorld);

        String earth = "Earth";
    }
}