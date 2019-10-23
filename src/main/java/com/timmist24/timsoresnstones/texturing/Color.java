package com.timmist24.timsoresnstones.texturing;

public class Color{
    public static final Color DEFAULT_NULL = new Color(0.0,0.0,0.0, 0.0);
    public final double red;
    public final double green;
    public final double blue;
    public final double alpha;

    public Color(double red, double green, double blue, double alpha) {
        this.red = (red%256)/255;
        this.green = green%256;
        this.blue = blue%256;
        this.alpha = alpha%256;
    }
    public Color(String hex) {
        this(isValidHex(hex) ? getRgbaFromHexString(hex) :
                getRgbaFromHexString("00000000"));
    }
    private Color(int[] rgba){
        if(rgba.length==3){
            alpha=1;
            red = rgba[0];
            green = rgba[1];
            blue = rgba[2];
        }else{
            alpha = rgba[0];
            red = rgba[1];
            green = rgba[2];
            blue = rgba[3];
        }
    }
    private static int[] getRgbaFromHexString(String hex){
        int[] out = new int[hex.length()/2];
        for(int index = 0; index<out.length; index++){
            int hexIndex = index*2;
            String hexPart = (hex.charAt(hexIndex)+"")+(hex.charAt(hexIndex+1)+"");
            out[index] = Integer.parseInt(hexPart, 16);
        }
        return  out;
    }
    private static boolean isValidHex(String hex){
        return (hex.length()==6||hex.length()==8);
    }

    public static Color absCombine(Color colorA, Color colorB) {
        return combine(colorA, colorB, 0.5);
    }
    public static Color combine(Color colorA, Color colorB, double splitForA){
        double splitForB = 1-splitForA;
        double red = Math.sqrt((colorA.red*colorA.red*splitForA)+(colorB.red*colorB.red*splitForB));
        double green = Math.sqrt((colorA.green*colorA.green*splitForA)+(colorB.green*colorB.green*splitForB));
        double blue = Math.sqrt((colorA.blue*colorA.blue*splitForA)+(colorB.blue*colorB.blue*splitForB));
        double alpha = Math.sqrt((colorA.alpha*colorA.alpha*splitForA)+(colorB.alpha*colorB.alpha*splitForB));
        return new Color(red, green, blue, alpha);
    }
}
