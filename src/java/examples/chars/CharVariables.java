/*
---
title: Char variables
exercises: |
    1. Why do you get an error if you delete line 6?
    2. What happens if you change the value of **aDigit** to **99** or **123**?
    3. What happens if you enclose **aPeriod** with double quotes instead of single quotes?
    4. What is the difference between using single quotes and double quotes?
    4. Define a char named **yourChar** and assign a value to it.
output: |
    a
    9
    .
...
*/

public class CharVariables 
{
    public static void main(String[] args) 
    {
        char aChar;
        aChar = 'a';
        System.out.println(aChar);

        char aDigit = '9';
        System.out.println(aDigit);

        char aPeriod = '.';
        System.out.println(aPeriod);       
    }
}