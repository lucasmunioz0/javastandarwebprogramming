package com.eduit.javaseweb.utils;

public final class StringUtils {
    public static String wordToCamelCase(String word){
        String aux = word.toLowerCase();
        return Character.toUpperCase(aux.charAt(0)) + aux.substring(1);
    }
}
