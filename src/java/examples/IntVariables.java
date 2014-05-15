/*
---
title: Int Variables
exercises: |
    1. Create an int named **yourInteger** and assign a value it.
    2. Why do you get an error if you delete line 6?
    3. What happens if you change the value of **anInteger** to **10.5** or **55.2**?
output: |
    0
    10
    -20
    13371337
...
*/

public class IntVariables 
{
    public static void main(String[] args) 
    {
        int myInteger;
        myInteger = 0;
        System.out.println(myInteger);   

        int anInteger = 10;
        System.out.println(anInteger);

        int anotherInteger = -20;
        System.out.println(anotherInteger);

        int yetAnotherInteger = 13371337;
        System.out.println(yetAnotherInteger);         
    }
}