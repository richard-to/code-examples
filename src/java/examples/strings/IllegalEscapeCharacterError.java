/*
---
title: Illegal escape character error
exercises: |
    1. What are the two illegal escape characters?
    2. Does the error message tell you where they are?
    3. How can this be fixed to print "Hello World\Earth\Universe?
output: |
    code/IllegalEscapeCharacter.java:5: error: illegal escape character
            String output1 = "Hello World\Earth\Universe";
                                          ^
    code/IllegalEscapeCharacter.java:5: error: illegal escape character
            String output1 = "Hello World\Earth\Universe";
                                                ^
    2 errors
...
*/

class IllegalEscapeCharacterError
{
    public static void main(String[] args)
    {
        String output1 = "Hello World\Earth\Universe";
        System.out.println(output1);
    }
}