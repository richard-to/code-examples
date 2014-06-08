/*
---
title: Elementary calculator
instructions: |
    Our alien overlords are building what they call a
    "Fractional Adding Machine" to simulate the addition
    abilities of an elementary school student.

    Unfortunately due to poor specifications the inputs
    are chars instead of doubles.

    To get around this issue, the integer and fractional
    parts of the numbers are passed in separately.

    **Given**

    - Valid values for the Integer part are the chars 0-9
    - Valid value for the Fractional part are the chars 0-9
    - If the integer part is 9 and the fractional part is 8, then the number is 9.08

    **Input**

    - The char `num1Int` is the integer part of the 1st number
    - The char `num1Frac` is the fractional part of the 1st number
    - The char `num2Int` is the integer part of the 2nd number
    - The char `num2Frac` is the fractional part of the 2nd number

    **Output**

    Print the sum of the two numbers

    **Sample**

        Input
        '9', '5', '7', '0'

        Output
        16.05
...
*/

public class ElementaryCalculator
{
    public static void add(char num1IntIn, char num1FracIn, char num2IntIn, char num2FracIn)
    {
        char num1Int = num1IntIn;
        char num1Frac = num1FracIn;
        char num2Int = num2IntIn;
        char num2Frac = num2FracIn;
    }
}