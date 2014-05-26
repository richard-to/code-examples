/*
---
title: String to Number
exercises: |
    1. Is the String **aNumber** equal to the int **anInt**?
    2. How can we parse a String to an int? to a double?
    3. Parse the value of **aNumber** to a short named **aShort**.
output: |
    20
    20
    false
    20
    true
    20.0
    true
...
*/
class StringToNumber
{
    public static void main(String[] args)
    {
        Boolean areEqual;
        
        String aNumber = "20";
        int anInt = 20;
                
        System.out.println(aNumber);
        System.out.println(anInt);
        
        areEqual = aNumber.equals(anInt);        
        System.out.println(areEqual);


        int anotherInt = Integer.parseInt(aNumber);
        System.out.println(anotherInt);
        
        areEqual = anotherInt == anInt;
        System.out.println(areEqual);
        
        
        double aDouble = Double.parseDouble(aNumber);
        System.out.println(aDouble);
        
        areEqual = anotherInt == aDouble;
        System.out.println(areEqual);
    }
}