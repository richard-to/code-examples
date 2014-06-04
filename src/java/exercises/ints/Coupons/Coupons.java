/*
---
title: Coupons
instructions: |
    Our alien overlords have scattered food coupons all over the world to show 
    that they are benevolent rulers.

    Unfortunately this plan backfired and the market is full of counterfeit coupons.

    They would like a way to validate the coupons.

    **Given**
    
    - Counterfeit and real coupons are positive integers
    - Counterfeit always contains 8 digits or less
    - The serial number contains exactly 8 digits
    - The serial number is not divisible by 2

    **Inputs**
    
    - The int `serialNumber` can be any positive integer

    **Output**
       
    Print to the console:

    - 0 if the coupon is not 8 digits and divisible by 2
    - 1 if the coupon is 8 digits or not divisible by 2
    - 2 if the coupon is 8 digits and not divisible by 2

    **Sample 1**
    
        Input
        12345677

        Output
        2

    **Sample 2**
    
        Input
        12345678

        Output
        1

    **Sample 3**
    
        Input
        123456

        Output
        0   
...
*/

public class Coupons
{
    public static void validate(int serialNumberIn)
    {
        int serialNumber = serialNumberIn;
    }
}