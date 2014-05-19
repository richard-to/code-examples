/*
---
title: Escaping characters
exercises: |
    1. What happens if you remove the backslashes from output1?
    2. What does **\n** do in **output4**? Why is a backslash necessary here?
    3. What does **\t** do in **output3**?
    4. What happens if you remove one of the backslashes from **output4**
    5. Are there other escape characters?
output: |
    I said, "Hello World."

    Hello World
    Hello Earth
    HelloUniverse

    Hello World    Hello Earth    Hello Universe

    Hello World\Earth\Universe
...
*/

class EscapingCharacters
{
    public static void main(String[] args)
    {
        String output1 = "I said, \"Hello World.\"";
        System.out.println(output1);

        String output2 = "\nHello World\nHello Earth\nHello Universe\n";
        System.out.println(output2);

        String output3 = "Hello World\tHello Earth\tHello Universe\n";
        System.out.println(output3);

        String output4 = "Hello World\\Earth\\Universe";
        System.out.println(output4);
    }
}