/*
---
title: Reserved keywords
exercises: |
    1. Why is `public` not a valid variable name?
    2. Try declaring a few variables using the keywords from this [list of Java keywords](http://en.wikipedia.org/wiki/List_of_Java_keywords).
    3. Since the error message is unclear, how can you know if you are accidentally using a reserved keyword?
output: |
    code/ReservedKeywords.java:5: error: not a statement
            int public = 451;
            ^
    code/ReservedKeywords.java:5: error: ';' expected
            int public = 451;
               ^
    code/ReservedKeywords.java:5: error: illegal start of expression
            int public = 451;
                ^
    3 errors
...
*/
public class ReservedKeywords
{
    public static void main(String[] args) 
    {
        int public = 451;
    }
}