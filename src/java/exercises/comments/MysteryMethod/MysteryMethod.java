/*
---
title: Mystery method
instructions: |
    The human resistance is working on machine that can
    encode messages.

    Unfortunately the programmer was captured and no
    one is sure which is the correct method since he
    named them poorly. He did, however, write comments.

    Can you uncomment the correct method?

    **Given**

    - The String `greeting` with value `Hello Universe`

    **Output**

        ESREVINu OLLEh
...
*/

public class MysteryMethod
{
    public static void main(String args[])
    {
        String greeting = "Hello Universe";

        // mysteryMethod1(greeting);

        // mysteryMethod2(greeting);

        // mysteryMethod3(greeting);

        // mysteryMethod4(greeting);
    }

    // This method prints the original input String
    public static void mysteryMethod1(String input)
    {
        String output = "";
        for (int i = 0; i < input.length(); ++i)
        {
            output += input.charAt(i);
        }
        System.out.print(output);
    }

    // This method prints the input String in reverse
    public static void mysteryMethod2(String input)
    {
        String output = "";
        for (int i = input.length() - 1; i >= 0; --i)
        {
            output += input.charAt(i);
        }
        System.out.print(output);
    }

    // This method prints the input String in reserve
    // and reverses the case of charcters.
    public static void mysteryMethod3(String input)
    {
        String output = "";
        for (int i = input.length() - 1; i >= 0; --i)
        {
            if (Character.isLowerCase(input.charAt(i)))
            {
                output += Character.toUpperCase(input.charAt(i));
            }
            else if (Character.isUpperCase(input.charAt(i)))
            {
                output += Character.toLowerCase(input.charAt(i));
            }
            else
            {
                output += input.charAt(i);
            }
        }
        System.out.print(output);
    }

    // This method prints the input String in all caps
    public static void mysteryMethod4(String input)
    {
        String output = "";
        for (int i = 0; i < input.length(); ++i)
        {
            if (Character.isLetter(input.charAt(i)))
            {
                output += Character.toUpperCase(input.charAt(i));
            }
            else
            {
                output += input.charAt(i);
            }
        }
        System.out.print(output);
    }
}