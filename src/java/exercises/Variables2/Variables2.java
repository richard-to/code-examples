/*
---
title: Variables 2
instructions: |
        <ol>
            <li>Create a variable of type <strong>int</strong> named <strong>number1</strong> with a value greater than 100.</li>
            <li>Create a variable of type <strong>int</strong> named <strong>number2</strong> with a value greater than 100.</li>
            <li>Multiply <strong>number1</strong> by <strong>number2</strong> and store the result in a variable named <strong>product</strong>.</li>
        </ul>
type: stub
...
*/

public class Variables2
{
    private int number1;
    private int number2;
    private int product;

    public Variables2()
    {
        //INSERT_SNIPPET

        this.number1 = number1;
        this.number2 = number2;
        this.product = product;
    }

    public int getNumber1()
    {
        return this.number1;
    }

    public int getNumber2()
    {
        return this.number2;
    }

    public int getProduct()
    {
        return this.product;
    }
}