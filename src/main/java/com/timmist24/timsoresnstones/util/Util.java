package com.timmist24.timsoresnstones.util;

public class Util {
    public static String regexContainsPlus(String[] search) {
        StringBuilder regexOut = new StringBuilder(".*(");
        for (String s : search) {
            if (regexOut.length() > 3) {
                regexOut.append("|");
            }
            if (search.length > 1) regexOut.append("(?:");
            regexOut.append(regexFind(s));
            if (search.length > 1) regexOut.append(")");
        }
        return regexOut + ").*";
    }

    public static String regexFind(String target) {
        String firstChar = target.charAt(0) + "";
        String rest = new StringBuilder(target).deleteCharAt(0).toString().toLowerCase();
        return ("(?:[^a-zA-Z]") + (firstChar.toLowerCase()) + ("|") + (firstChar.toUpperCase()) + (")") + (rest);
    }
}
