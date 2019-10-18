package com.timmist24.timsoresnstones.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataRetrival {
    private static final String NESTING_DIRECTORY_PATH= System.getProperty("user.dir").substring(0,System.getProperty("user.dir").lastIndexOf(File.separator))+"\\src\\main";

    public static BufferedWriter langOut = null;
    public static boolean outputsInit() {
        try {
            File lang = new File(NESTING_DIRECTORY_PATH, "resources\\assets\\tosm\\lang\\en_us.lang");
            if (lang.exists()) lang.delete();
            langOut = new BufferedWriter(new FileWriter(lang.toString()));
            return true;
        }
        catch (IOException e){
            return false;
        }

    }
    public static boolean outputsClose() {
        try {
            langOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String stringArrayToId(String[] components){
        StringBuilder out = new StringBuilder();
        for(String part: components){
            out.append(part.toLowerCase().replaceAll("\\s", "_")).append("_");
        }
        out.deleteCharAt(out.length()-1);
        return out.toString();
    }
    public static String idToEnglishName(String idIn){
        StringBuilder out = new StringBuilder(idIn.replaceAll("_", " "));
        out.delete(0, 5);
        out.replace(0,1, (out.charAt(0)+"").toUpperCase());
        return out.toString();
    }
}
