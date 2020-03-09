package com.timmist24.timsoresnstones.texturing;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

public class Color {
    private static final int DEFAULT_TOLERANCE = 30;
//    private static final List<Image> ORE_OVERLAYS = new ArrayList<>();// establish known ore patterns and stone textures first
//    private static final List<Image> GROUND_IMAGES = new ArrayList<>();// can be pre initalised bylist of ground minerals

    public static Color extractColor(Item item, int itemDamage, BufferedImage textureMap, int level, Boolean useEntireTexture){// filter damaged for {glowstone?, quartz, benitoite, ardite
        try {
            TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(item, itemDamage);
            BufferedImage sub = textureMap.getSubimage(sprite.getOriginX()>>level, sprite.getOriginY()>>level, sprite.getIconWidth()>>level, sprite.getIconHeight()>>level);
            int red = 0;
            int green = 0;
            int blue = 0;
            int counter = 0;
            int[] pixel100x100 = new int[100*100];
            Color firstPixelColor = new Color(sub.getRGB(0,0));
            BufferedImage pixleDebug = new BufferedImage(200,100,2);

            Arrays.fill(pixel100x100, sub.getRGB(0,0));

            pixleDebug.setRGB(0,0,100, 100, pixel100x100, 0, pixleDebug.getHeight());

            for(int pixel: sub.getRGB(0,0, sub.getWidth(), sub.getHeight(), new int[sub.getWidth()*sub.getHeight()], 0, sub.getWidth())){
                Color pileColor = new Color(pixel);

                Arrays.fill(pixel100x100, pixel);
                pixleDebug.setRGB(100,0,100, 100, pixel100x100, 0, pixleDebug.getHeight());


                boolean notSimilar = !similarColors(firstPixelColor, pileColor);
                if (useEntireTexture||(((pixel)!=0) && notSimilar)){
                    red+=pileColor.red;
                    green+=pileColor.green;
                    blue+=pileColor.blue;
                    counter++;
                }

            }

            return new Color((float)red/counter, (float)green/counter, (float)blue/counter, 255);
        }catch (Throwable e){
            TimsOresNStonesMain.logger.error("Failed to extract color from "+item.getUnlocalizedName()+". Because of "+e.toString()+". Retuning a random color");
            return  Color.random(new Random());//thorium, boron, lithium, magnesium
        }
    }
    @Contract(pure = true)
    public static boolean similarColors(@NotNull Color colorA, @NotNull Color colorB) {
        int difARG = Math.abs(colorA.red-colorA.green);
        int difBRG = Math.abs(colorB.red-colorB.green);

        int difARB = Math.abs(colorA.red-colorA.blue);
        int difBRB = Math.abs(colorB.red-colorB.blue);

        int difAGB = Math.abs(colorA.green-colorA.blue);
        int difBGB = Math.abs(colorB.green-colorB.blue);

        int difAA = Math.abs(colorA.alpha-colorB.alpha);
        int difRR = Math.abs(colorA.red-colorB.red);
        int difGG = Math.abs(colorA.green-colorB.green);
        int difBB = Math.abs(colorA.blue-colorB.blue);


        boolean test0 = difAA<=DEFAULT_TOLERANCE/2;
        boolean test1 = Math.abs(difARG-difBRG) <= Color.DEFAULT_TOLERANCE;
        boolean test2 = Math.abs(difARB-difBRB) <= Color.DEFAULT_TOLERANCE;
        boolean test3 = Math.abs(difAGB-difBGB) <= Color.DEFAULT_TOLERANCE;

        boolean test4 = difRR <= DEFAULT_TOLERANCE*2;
        boolean test5 = difGG <= DEFAULT_TOLERANCE*2;
        boolean test6 = difBB <= DEFAULT_TOLERANCE*2;

        return test0&&test1&&test2&&test3&&test4&&test5&&test6;
    }

//    @NotNull
//    @Contract(value = "_ -> new", pure = true)
//    public static Color greighScale(@NotNull Color color){
//        float avrage = (float) ((color.red+color.green+color.blue)/3);
//        return new Color(avrage,avrage,avrage);
//    }
    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static Color absCombine(Color colorA, Color colorB) {
        return combine(colorA, colorB, 0.5);
    }
    @NotNull
    @Contract(value = "_, _, _ -> new", pure = true)
    public static Color combine(@NotNull Color colorA, @NotNull Color colorB, double splitForA) {
        if(splitForA<0){
            return absCombine(colorA,colorB);
        }
        double splitForB = 1 - splitForA;
        double red = Math.sqrt((colorA.red * colorA.red * splitForA) + (colorB.red * colorB.red * splitForB));
        double green = Math.sqrt((colorA.green * colorA.green * splitForA) + (colorB.green * colorB.green * splitForB));
        double blue = Math.sqrt((colorA.blue * colorA.blue * splitForA) + (colorB.blue * colorB.blue * splitForB));
        double alpha = colorA.alpha;
        return new Color(red, green, blue, alpha*255);
    }
    @NotNull
    @Contract("_ -> new")
    public static Color random(@NotNull Random random) {
        return new Color(random.nextInt(255), random.nextInt(255),random.nextInt(255), 255);
    }

    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    public Color(double red, double green, double blue, double alpha) {
        this.red = (int) (red%256);
        this.green = (int) (green%256);
        this.blue = (int) (blue%256);
        this.alpha = (int) (alpha%256);
    }
    public Color(int colorAsInt){this((colorAsInt >> 16) & 0xff, (colorAsInt >> 8) & 0xff , (colorAsInt) & 0xff, (colorAsInt >> 24) & 0xff);}
    public Color(String hex) { this(Integer.parseInt(hex, 16));}
    public Color(double red, double green, double blue){ this(red,green,blue,255); }

    public int toInt() {
        int color = 0;
        color += (this.alpha) <<24;
        color += (this.red ) <<16;
        color += (this.green ) <<8;
        color += this.blue;
        return color;
    }
    @Override
    public String toString(){
        return "a:"+alpha+",r:"+red+",g:"+green+",b:"+blue;
    }
}
