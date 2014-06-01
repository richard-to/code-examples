/*
---
title: Double limits
exercises: |
    1. Do the constant values `Double.MAX_VALUE` and `Double.MIN_VALUE` work the same as `Int.MAX_VALUE` and `Int.MIN_VLAUE`?
    2. What about the second approach of using negative Double.MAX_VALUE? Why is this different from Double.MIN_VALUE?
    3. What happens if you add 1.0 to `maxDouble`? What about 10000.0? 10000000.0? Double.MAX_VALUE?
    4. What happens if you subtract 1.0 from `minDouble`? What about -10000.0? -10000000.0? -Double.MAX_VALUE?    
output: |
    1.7976931348623157E308
    -1.7976931348623157E308
...
*/

public class DoubleLimits 
{
    public static void main(String[] args) 
    {
        double maxDouble;
        double minDouble;
        
        maxDouble = Double.MAX_VALUE;
        minDouble = Double.MIN_VALUE;
        System.out.println(maxDouble);   
        System.out.println(minDouble);  
        
        maxDouble = Double.MAX_VALUE;
        minDouble = -Double.MAX_VALUE;
        System.out.println(maxDouble);   
        System.out.println(minDouble);       
    }
}