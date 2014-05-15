/*
---
title: Double Data Type
exercises: |
    1. Declare a double named **yourDouble** and assign value it.
    2. Why do you get an error if you delete line 8?
output: |
    10.5
    0.0
    10.5
    -20.5
    30.0
...
*/

public class DoubleDataType 
{
    public static void main(String[] args) 
    {
        System.out.println(10.5);        

        double myDouble;
        myDouble = 0;
        System.out.println(myDouble);   

        double anDouble = 10.5;
        System.out.println(anDouble);

        double anotherDouble = -20.5;
        System.out.println(anotherDouble);

        double yetAnotherDouble = 30;
        System.out.println(yetAnotherDouble);         
    }
}