package com.timmist24.timsoresnstones.util;

public class Util {
    public static String regexContainsPlus(String[] search){
        StringBuilder regexOut = new StringBuilder(".*(");
        for(String s: search){
            String firstChar = s.charAt(0)+"";
            String rest = new StringBuilder(s).deleteCharAt(0).toString().toLowerCase();

            if(regexOut.length()>3){
                regexOut.append("|");
            }
            if(search.length>1) regexOut.append("(?:");
            regexOut.append("(?:[^a-zA-Z]").append(firstChar.toLowerCase()).append("|").append(firstChar.toUpperCase()).append(")").append(rest);
            if(search.length>1)regexOut.append(")");
        }
        return regexOut + ").*";
    }
}
