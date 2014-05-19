/*
---
title: Readable variables names
exercises: |
    1. What is the purpose of the variable **$dsf$_dfs**?
    2. Which variable names are easiest to read?
    3. Is it beneficial to name variables clearly?
    4. How would you name **$dsf$_dfs** if it represents degrees in fahrenheit?
output: |
    451
    20
    3430
    3430
...
*/

public class ReadableVariableNames
{
    public static void main(String[] args) 
    {
        int $dsf$_dfs = 451;
        int _$d21sf$_d99fs = 20;
        int numEarthlingsAbductedAliens = 3430;
        int num_earthlings_abducted_aliens = 3430;

        System.out.println($dsf$_dfs);
        System.out.println(_$d21sf$_d99fs);
        System.out.println(numEarthlingsAbductedAliens);
        System.out.println(num_earthlings_abducted_aliens);
    }
}