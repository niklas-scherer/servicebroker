package com.sniperfuchs.servicebroker.util;

public class IdentifierValidator {

    public static boolean validate(String input) {
        if(input == null || input.isEmpty()) {
            return false;
        }
        String regex = "[-A-Za-z0-9_~]+";
        return input.matches(regex);
    }
}
