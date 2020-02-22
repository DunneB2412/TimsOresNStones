package com.timmist24.timsoresnstones.texturing;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.util.GuiClass;
import com.timmist24.timsoresnstones.util.TestGui;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

public class Color implements IItemColor, IBlockColor {
    private static final int DEFAULT_TOLERANCE = 30;

    public static Color extractColor(Item item, int itemDammage, BufferedImage textureMap, int levle, Boolean useEntireTexture){
        try {
            //Thread test = new GuiClass();
            //test.start();
            TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(item, itemDammage);
            BufferedImage sub = textureMap.getSubimage(sprite.getOriginX()>>levle, sprite.getOriginY()>>levle, sprite.getIconWidth()>>levle, sprite.getIconHeight()>>levle);
            int red = 0;
            int green = 0;
            int blue = 0;
            int counter = 0;
            int[] pixle100x100 = new int[100*100];
            Color firstPixleColor = new Color(sub.getRGB(0,0));
            BufferedImage pixleDebug = new BufferedImage(200,100,2);

            Arrays.fill(pixle100x100, sub.getRGB(0,0));

            pixleDebug.setRGB(0,0,100, 100, pixle100x100, 0, pixleDebug.getHeight());

            for(int pixle: sub.getRGB(0,0, sub.getWidth(), sub.getHeight(), new int[sub.getWidth()*sub.getHeight()], 0, sub.getWidth())){
                Color pixleColor = new Color(pixle);

                Arrays.fill(pixle100x100, pixle);
                pixleDebug.setRGB(100,0,100, 100, pixle100x100, 0, pixleDebug.getHeight());


                boolean notSimiler = !similerColors(firstPixleColor, pixleColor);
                if (useEntireTexture||(((pixle)!=0) && notSimiler)){
                    red+=pixleColor.red;
                    green+=pixleColor.green;
                    blue+=pixleColor.blue;
                    counter++;
                }

            }

            return new Color(red/counter, green/counter, blue/counter, 255);
        }catch (Throwable e){
            TimsOresNStonesMain.logger.error("Failed to extract color from "+item.getUnlocalizedName()+". Because of "+e.toString()+". Retruning a random collor");
            return  Color.random(new Random());
        }
    }

    private static boolean similerColors(Color colorA, Color colorB) {
        int difARG = (int) Math.abs(colorA.red-colorA.green);
        int difBRG = (int) Math.abs(colorB.red-colorB.green);
        int difARB = (int) Math.abs(colorA.red-colorA.blue);
        int difBRB = (int) Math.abs(colorB.red-colorB.blue);
        int difAGB = (int) Math.abs(colorA.green-colorA.blue);
        int difBGB = (int) Math.abs(colorB.green-colorB.blue);

        int difAA = (int) Math.abs(colorA.alpha-colorB.alpha);
        int difRR = (int) Math.abs(colorA.red-colorB.red);
        int difGG = (int) Math.abs(colorA.green-colorB.green);
        int difBB = (int) Math.abs(colorA.blue-colorB.blue);

        boolean test1 = Math.abs(difARG-difBRG) < Color.DEFAULT_TOLERANCE;
        boolean test2 = Math.abs(difARB-difBRB) < Color.DEFAULT_TOLERANCE;
        boolean test3 = Math.abs(difAGB-difBGB) < Color.DEFAULT_TOLERANCE;

        return Math.abs(difARG-difBRG) < Color.DEFAULT_TOLERANCE &&
                (Math.abs(difARB-difBRB) < Color.DEFAULT_TOLERANCE &&
                        (Math.abs(difAGB-difBGB) < Color.DEFAULT_TOLERANCE)&&
                        difAA<DEFAULT_TOLERANCE/100);/* &&
                        difRR<DEFAULT_TOLERANCE*50&&
                        difGG<DEFAULT_TOLERANCE*50&&
                        difBB<DEFAULT_TOLERANCE*50);*/
    }

    public static Color absCombine(Color colorA, Color colorB) {
        return combine(colorA, colorB, 0.5);
    }
    public static Color combine(Color colorA, Color colorB, double splitForA) {
        double splitForB = 1 - splitForA;
        double red = Math.sqrt((colorA.red * colorA.red * splitForA) + (colorB.red * colorB.red * splitForB));
        double green = Math.sqrt((colorA.green * colorA.green * splitForA) + (colorB.green * colorB.green * splitForB));
        double blue = Math.sqrt((colorA.blue * colorA.blue * splitForA) + (colorB.blue * colorB.blue * splitForB));
        double alpha = colorA.alpha;
        return new Color(red, green, blue, alpha*255);
    }
    public static Color random(Random random) {
        return new Color(random.nextInt(255), random.nextInt(255),random.nextInt(255), 255);
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
    public Color(int colorAsInt){this((colorAsInt >> 16) & 0xff, (colorAsInt >> 8) & 0xff , (colorAsInt) & 0xff, (colorAsInt >> 24) & 0xff);}
    public Color(double percentAlpha, Color color){ this(color.red, color.green, color.blue, (color.alpha*255)*percentAlpha/100);}
    public Color(String hex) { this(Integer.parseInt(hex, 16));}

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
