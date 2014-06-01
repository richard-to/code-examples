/*
---
title: Chars and ints
exercises: |
    1. Why does `anotherChar5` print `5` and not `53`? What does this say about chars and ints?
    2. Why are `num5` and `char5` not equal?
    3. Why are `num53` and `char5` equal?
    4. What char value is equal to the int value `55`? 
    5. What int value is equal to the char value `'3'`?
    6. Define a char named `yourChar` and assign it the integer value `1`? What is the result?
output: |
    5
    53
    5
    5
    false
    true
    true
...
*/

public class CharsAndInts
{
    public static void main(String[] args) 
    {
        int num5 = 5;
        int num53 = 53;
        char char5 = '5';
        char anotherChar5 = 53;

        System.out.println(num5);
        System.out.println(num53);        
        System.out.println(char5);
        System.out.println(anotherChar5);            


        boolean areEqual;

        areEqual = num5 == char5;
        System.out.println(areEqual); 

        areEqual = char5 == num53;
        System.out.println(areEqual);

        areEqual = char5 == anotherChar5;
        System.out.println(areEqual);        
    }
}