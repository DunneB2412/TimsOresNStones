package com.timmist24.timsoresnstones.texturing;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.client.resources.IResource;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Color implements IItemColor, IBlockColor {
    public static Color extractColor(ResourceLocation resourceLocation){

        try (Resource res = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation)) {
            BufferedImage image = ImageIO.read(res.getInputSteram());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            //String searchKey = "hex color code for"+title
            int red=0;
            int green=0;
            int blue=0;
            resourceLocation.getResourceDomain();
            File imageFile = new File(resourceLocation.getResourcePath(),"image.png");
            BufferedImage image = ImageIO.read(imageFile);
            return new Color(red, green, blue, 255);
        } catch (IOException e) {
            return Color.random(new Random(resourceLocation.hashCode()));
        }
    }
    public static Color absCombine(Color colorA, Color colorB) {
        return combine(colorA, colorB, 0.5);
    }
    public static Color combine(Color colorA, Color colorB, double splitForA){
        double splitForB = 1-splitForA;
        double red = Math.sqrt((colorA.red*colorA.red*splitForA)+(colorB.red*colorB.red*splitForB));
        double green = Math.sqrt((colorA.green*colorA.green*splitForA)+(colorB.green*colorB.green*splitForB));
        double blue = Math.sqrt((colorA.blue*colorA.blue*splitForA)+(colorB.blue*colorB.blue*splitForB));
        //double alpha = Math.sqrt((colorA.alpha*colorA.alpha*splitForA)+(colorB.alpha*colorB.alpha*splitForB));
        return new Color(red, green, blue, colorA.alpha);
    }




    private final double red;
    private final double green;
    private final double blue;
    private final double alpha;

    public Color(double red, double green, double blue, double alpha) {
        this.red = red%256;
        this.green = green%256;
        this.blue = blue%256;
        this.alpha = (alpha%256)/255;
    }
    public Color(double percentAlpha, Color color){ this(color.red, color.green, color.blue, (color.alpha*255)*percentAlpha/100);}
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

    public static Color random(Random random) {
        return new Color(random.nextInt(255), random.nextInt(255),random.nextInt(255), 255);
    }

    public int toInt() {

        int color = 0;
        color += (int) (this.alpha * 255) <<24;
        color += (int) (this.red ) <<16;
        color += (int) (this.green ) <<8;
        color += (int) (this.blue);
        return color;
    }
    @Override
    public String toString(){
        return "a:"+alpha+",r:"+red+",g:"+green+",b:"+blue;
    }



    /**
     * should tale over local handlers
     * unimplimented yet
     */
    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return 0;
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        return 0;
    }
}
