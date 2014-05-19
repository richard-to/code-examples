/*
---
title: Int variables
exercises: |
    1. Why do you get an error if you delete line 6?
    2. What happens if you change the value of **anInteger** to **10.5** or **55.2**?
    3. Define an int named **yourInteger** and assign a value to it.
output: |
    -200
    25
    1337
    9000
...
*/

public class IntVariables 
{
    public static void main(String[] args) 
    {
        int negativeInteger;
        negativeInteger = -200;
        System.out.println(negativeInteger);   

        int anInteger = 25;
        System.out.println(anInteger);

        int anotherInteger = 1337;
        System.out.println(anotherInteger);

        int biggerInteger = 9000;
        System.out.println(biggerInteger);
    }
}