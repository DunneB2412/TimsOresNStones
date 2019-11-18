package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.texturing.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mineral implements Comparable<Mineral>{
    private static final List<Mineral> MINERALS = new ArrayList<>();  // turn into an unmutable array at some point. maby
    private static final Random RANDOM = new Random();
    private static final float WEIGHT_FACTOR = 0.33f;
    private static final int UNSTABILITY_OIL_THRESHOLD = 30;
    static {
        MINERALS.add(new Mineral("empty", MineralVariant.METAL, false, 0.0f, 0, new Color("bebebe")));
        MINERALS.addAll(InitMineralsMethod.BRUTE_FORCE.getInitMinerals().initiateMinerals());

    }


    public static Mineral getMineral(int i) {
        return MINERALS.get(i);
    }
    public static int numberOfMinerals(){
        return MINERALS.size();
    }



    public final String title;
    public final Color[] colors;
    private final int unstability;
    private final MineralVariant type;
    private final boolean isOilSoluble;
    private final float weightPerUnit;
    //private final Item parent; use to generate translation and maby to extract existing color
    Mineral(String title, MineralVariant type, boolean isOilSoluble, float weightPerUnit, int unstability, Color... color) {
        this.title = title;
        this.type = type;
        this.unstability = unstability;
        this.isOilSoluble = isOilSoluble;
        this.weightPerUnit = weightPerUnit;
        this.colors = color;
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
