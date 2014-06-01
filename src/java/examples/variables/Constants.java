/*
---
title: Constant variables
exercises: |
    1. Change the value of `SECONDS_IN_MINUTE` to 120 within the main method.
    2. How is the syntax for declaring constants different than say instance variables?
    3. Create constants variables of type, `boolean`, `float`, and `char`.
    4. What may be a good use for constant variables?
    5. Why may it be a good convention to use uppercase letters for constants?
output: |
    60
    Hello Universe
...
*/
public class Constants
{
    public static final int SECONDS_IN_MINUTE = 60;
    public static final String SPECIAL_MESSAGE = "Hello Universe";

    public static void main(String[] args) 
    {
        System.out.println(SECONDS_IN_MINUTE);
        System.out.println(SPECIAL_MESSAGE);
    }
}