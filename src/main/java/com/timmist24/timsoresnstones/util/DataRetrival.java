package com.timmist24.timsoresnstones.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataRetrival {
    public static BufferedWriter langOut = null;
    public static void outputsInit() {
        try {
            File lang = new File("C:\\Users\\dunne\\Desktop\\projects\\dev\\tim\\TimsOresNStones\\src\\main\\resources\\assets\\tosm\\lang", "en_us.lang");
            if (lang.exists()) lang.delete();
            langOut = new BufferedWriter(new FileWriter(lang.toString()));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void outputsClose() {
        try {
            langOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getId(String[] components){
        StringBuilder out = new StringBuilder("");
        for(String part: components){
            out.append(part.toLowerCase().replaceAll("\\s", "_")+"_");
        }
        out.deleteCharAt(out.length()-1);
        return out.toString();
    }
}
