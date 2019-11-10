package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mineral implements Comparable<Mineral>{
    private static final List<Mineral> MINERALS = new ArrayList<>();
    private static final Random RANDOM = new Random();
    private static final float WEIGHT_FACTOR = 0.33f;
    private static final int UNSTABILITY_OIL_THRESHOLD = 30;
    static {
        MINERALS.add(new Mineral("lowest", MineralVariant.METAL, false, 0.0f, 0, new Color("00000000")));
    }
    public static String add(Mineral mineral){
        if(!MINERALS.contains(mineral)) {
            MINERALS.add(mineral);
            return mineral.title;
        }
        return null;
    }
    public static String getMinerals() {
        return MINERALS+"";
    }
    public static Mineral getMineral(int i) {
        return MINERALS.get(i);
    }
    public static int numberOfMinerals(){
        return MINERALS.size();
    }







    public final String title;
    public final Color color;
    private final int unstability;
    private final MineralVariant type;
    private final boolean isOilSoluble;
    private final float weightPerUnit;
    public Mineral(String title, MineralVariant type, boolean isOilSoluble, float weightPerUnit, int unstability, Color color) {
        this.title = title;
        this.type = type;
        this.unstability = unstability;
        this.isOilSoluble = isOilSoluble;
        this.weightPerUnit = weightPerUnit;
        this.color = color;
        //OreDictionary.
    }

    public final String getOreDictName(){
        return type.toString();
    }
    public boolean isOilSoluble() {
        if(unstability>UNSTABILITY_OIL_THRESHOLD){
            return RANDOM.nextBoolean();
        }
        return isOilSoluble;
    }
    public float getWeightPerUnit() {
        float randomChange = RANDOM.nextFloat()*WEIGHT_FACTOR;
        if(RANDOM.nextBoolean()){
            return weightPerUnit+randomChange;
        }
        return weightPerUnit- randomChange;
    }
    public MineralVariant getType() {
        return type;
    }
    public String getTranslation(){
        throw new UnsupportedOperationException();
    }
    @Override
    public String toString(){
       return "Mineral:"+title+".";
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
