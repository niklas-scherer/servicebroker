package com.sniperfuchs.servicebroker.util;

public class IdentifierValidator
{
    private static String regex = "[-A-Za-z0-9_~]";
    public static boolean validate(String input)
    {
        if(input == null || input.isEmpty())
        {
            return false;
        }
        return input.matches(regex);
    }
}
