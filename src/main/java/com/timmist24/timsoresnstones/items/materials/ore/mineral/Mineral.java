package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.texturing.Color;

import java.util.Random;

public class Mineral implements Comparable<Mineral>{
    public static final Mineral LOWEST = new Mineral("low", MineralVariant.METAL, false, 0.0f, 0, new Color("00000000"), 0);
    private static final Random RANDOM = new Random();
    private static final int DEFAULT_RANDOM_LIM = 45;
    private static final float DEFAULT_UNSTABILITY_DEVIDER = 3.0f;

    private final int unstability;// bigger is more unstable, 0 for none
    public final String title;
    private final MineralVariant type;
    private final boolean isOilSoluble;
    private float weightPerUnit;
    private int quantity; // 1 = 1 tiny
    public final Color color;


    public Mineral(String title, MineralVariant type, boolean isOilSoluble, float weightPerUnit,  int unstability, Color color) {
        this(title, type, isOilSoluble, weightPerUnit, unstability, color, RANDOM.nextInt(DEFAULT_RANDOM_LIM));
    }
    public Mineral(String title,MineralVariant type, boolean isOilSoluble, float weightPerUnit, int unstability, Color color, int quantity){
        this.title = title;
        this.type = type;
        this.unstability = unstability;
        this.isOilSoluble = isOilSoluble;
        this.weightPerUnit = weightPerUnit;
        this.color = color;
        this.quantity = quantity;
    }
    public Mineral(Mineral other, int quantity){
        this(other.title, other.type, other.isOilSoluble, other.weightPerUnit, other.unstability, other.color, quantity);
    }

    public Mineral extractMaterial(double eficancy){
        int extctedQuantity = (int) (quantity*(eficancy/100)); //this should round down
        quantity = Math.max(0, quantity - extctedQuantity);
        return new Mineral(this, extctedQuantity);
    }
    public int getQuantity(){
        return quantity;
    }
    public boolean isOilSoluble(){
        if(RANDOM.nextInt(unstability)>10) {
            return RANDOM.nextBoolean();
        }
        return isOilSoluble;
    }
    public float getWeightPerUnit(){
        float randomChange = RANDOM.nextInt(unstability)/3.0f;
        if(RANDOM.nextBoolean()) {
            return weightPerUnit+randomChange;
        }
        return  weightPerUnit-randomChange;
    }
    public String getTranslation(){
        return "";
    }

    @Override
    public String toString(){
       return "Mineral:"+title+"["+quantity+"]";
    }
    @Override
    public int compareTo(Mineral other) {
        return quantity-other.quantity;
    }
    @Override
    public boolean equals(Object other){
        if(other.getClass().equals(Mineral.class)) {
            return title.equals(((Mineral)other).title);
        }
        return false;
    }
}
