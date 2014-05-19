/*
---
title: Float data type
exercises: |
    1. Are floats larger or smaller than doubles?
    2. Are floats more like ints or doubles?
    3. Do the constant values **Float.MAX_VALUE** and **Float.MIN_VALUE** work the same as **Int.MAX_VALUE** and **Int.MIN_VLAUE**?
    4. What about the second approach of using negative **Float.MAX_VALUE**? Why is this different from **Float.MIN_VALUE**?
    5. What happens if you add **1.0** to **maxFloat**? What about **10000.0**? **10000000.0**?
    6. What happens if you subtract **1.0** from **minFloat**? What about **-10000.0**? **-10000000.0**?
output: |
    20.0
    3.4028235E38
    1.4E-45
    3.4028235E38
    -3.4028235E38
...
*/

public class FloatDataType
{
    public static void main(String[] args) 
    {
        float aFloat;
        aFloat= 20.0;
        System.out.println(aFloat);

        float maxFloat;
        float minFloat;
        
        maxFloat = Float.MAX_VALUE;
        minFloat = Float.MIN_VALUE;
        System.out.println(maxFloat);   
        System.out.println(minFloat);  
        
        maxFloat = Float.MAX_VALUE;
        minFloat = -Float.MAX_VALUE;
        System.out.println(maxFloat);   
        System.out.println(minFloat); 
    }
}