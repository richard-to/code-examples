/*
---
title: Accessing characters
exercises: |
    1. What data type does the method **charAt** return?
    2. How would you get the 1st character in the String?
    3. How would you get the last character in the String?
    4. What if you tried getting the 100th character?
output: |
    W
...
*/

class AccessingCharacters
{
    public static void main(String[] args)
    {
        String output1 = "Hello World";
        char char7 = output1.charAt(6);
        System.out.println(char7);
    }
}