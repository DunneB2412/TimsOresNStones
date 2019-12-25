package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mineral implements Comparable<Mineral>{
    private static final List<Mineral> MINERALS = new ArrayList<>();  // turn into an unmutable array at some point. maby
    private static final Random RANDOM = new Random();
    private static final float WEIGHT_FACTOR = 0.33f;
    private static final int UNSTABILITY_OIL_THRESHOLD = 30;
    static {
        MINERALS.add(new Mineral("empty", false, 0.0f, 0, Blocks.STONE, 0));
        MINERALS.addAll(InitMineralsMethod.BRUTE_FORCE.getInitMinerals().initiateMinerals());

    }
    public static Mineral getMineral(int i) {
        return MINERALS.get(i);
    }
    public static int numberOfMinerals(){
        return MINERALS.size();
    }
    public static void SetUpCollor(){
        TextureManager manager = Minecraft.getMinecraft().renderEngine;
        manager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        int levle = 0;
        int width = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, levle, GL11.GL_TEXTURE_WIDTH);
        int height = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, levle, GL11.GL_TEXTURE_HEIGHT);
        int size = width*height;
        IntBuffer buffer = BufferUtils.createIntBuffer(size);
        GL11.glGetTexImage(GL11.GL_TEXTURE_2D, levle, GL12.GL_BGRA,GL12.GL_UNSIGNED_INT_8_8_8_8_REV, buffer);
        int[] rgbArray = new int[size];
        buffer.get(rgbArray);
        BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        bufferedImage.setRGB(0,0, width, height, rgbArray, 0, width);

        for(Mineral mineral: MINERALS){
            IBlockState state = mineral.parent.getBlockState().getValidStates().get(mineral.parentsMeta);
            Item itemDropped = mineral.parent.getItemDropped(state,RANDOM,0) ;
            int itemDammage = mineral.parent.damageDropped(state);
            Color color = Color.extractColor(itemDropped, itemDammage, bufferedImage, levle, mineral.equals(MINERALS.get(0)));
            setMineralValues(mineral, MineralVariant.METAL, color, Color.combine(color, new Color("aaaaaa"), 0.75));
        }
    }

    private static void setMineralValues(Mineral mineral, MineralVariant type, Color... colors) {
        mineral.colors = colors;
        mineral.type = type;
    }


    public final String title;
    public Color[] colors;
    private final int unstability;
    private MineralVariant type;
    private final boolean isOilSoluble;
    private final float weightPerUnit;
    private final Block parent;
    private final int parentsMeta;
    Mineral(String title, boolean isOilSoluble, float weightPerUnit, int unstability, Block block, int meta){
        this.title = title;
        this.unstability = unstability;
        this.isOilSoluble = isOilSoluble;
        this.weightPerUnit = weightPerUnit;
        this.parent = block;
        this.parentsMeta = meta;
    }

//    public boolean isOilSoluble() {
//        if(unstability>UNSTABILITY_OIL_THRESHOLD){
//            return RANDOM.nextBoolean();
//        }
//        return isOilSoluble;
//    }
//    public float getWeightPerUnit() {
//        float randomChange = RANDOM.nextFloat()*WEIGHT_FACTOR;
//        if(RANDOM.nextBoolean()){
//            return weightPerUnit+randomChange;
//        }
//        return weightPerUnit- randomChange;
//    }
    public MineralVariant getType() {
        return type;
    }
    @Override
    public String toString(){
       return "Mineral:"+title+"<"+type+">";
    }
    @Override
    public int compareTo(Mineral other) {
        return title.compareTo(other.title);
    }
    @Override
    public boolean equals(Object other){
        if(other.getClass().equals(Mineral.class)) {
            return title.equals(((Mineral)other).title);
        }
        return false;
    }
}
