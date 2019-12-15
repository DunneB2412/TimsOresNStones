package com.timmist24.timsoresnstones.texturing;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Color implements IItemColor, IBlockColor {

    public static Color extractColor(ItemStack stack, BufferedImage textureMap, int levle){
        try {
            TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(stack.getItem());
            BufferedImage sub = textureMap.getSubimage(sprite.getOriginX()>>levle, sprite.getOriginY()>>levle, sprite.getIconWidth()>>levle, sprite.getIconHeight()>>levle);
            Color out = new Color(0x00000000);
            int counter = 0;
            for(int pixle: sub.getRGB(0,0, sub.getWidth(), sub.getHeight(), new int[sub.getWidth()*sub.getHeight()], 0, sub.getWidth())){
                Color pixleColor = new Color(pixle);
                if (((pixle & 0xffffff)!=0) && ((pixle & 0xff000000)!=0)){// overall difference in rgb by 10, greigh filter, got inksack insted of lapiz(dye0), item border brakes texture
                    if(counter <= 0){
                        out = pixleColor;
                    }else {
                        out = absCombine(out, pixleColor);
                    }
                    counter++;
                }
            }
            return out;
        }catch (Throwable e){
             return  Color.random(new Random(stack.hashCode()));
        }
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
