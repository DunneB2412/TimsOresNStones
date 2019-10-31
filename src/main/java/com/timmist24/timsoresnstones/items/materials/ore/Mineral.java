package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.texturing.Color;

import java.util.Random;

public class Mineral implements Comparable<Mineral>{
    static final Random RANDOM = new Random();
    private static final int DEFAULT_RANDOM_LIM = 45;
    public final String title;
    final boolean isOilSoluble;
    float weightPerUnit;
    private int quantity; // 1 = 1 tiny
    public final Color color;

    public Mineral(String title, boolean isOilSoluble, float weightPerUnit, Color color) {
        this(title, isOilSoluble, weightPerUnit, color, RANDOM.nextInt(DEFAULT_RANDOM_LIM));
    }
    public Mineral(String title, boolean isOilSoluble, float weightPerUnit, Color color, int quantity){
        this.title = title;
        this.isOilSoluble = isOilSoluble;
        this.weightPerUnit = weightPerUnit;
        this.color = color;
        this.quantity = quantity;
    }
    public Mineral extractMaterial(double eficancy){
        int extctedQuantity = (int) (quantity*(eficancy/100)); //this should round down
        quantity = Math.max(0, quantity - extctedQuantity);
        return new Mineral(title, isOilSoluble, weightPerUnit, color, extctedQuantity);
    }
    public int getQuantity(){
        return quantity;
    }
    public boolean isOilSoluble(){
        return isOilSoluble;
    }
    public float getWeightPerUnit(){
        return weightPerUnit;
    }
    public float getNetWeight() {
        return getWeightPerUnit()*quantity;
    }
    @Override
    public String toString(){
       return "Mineral:"+title+"["+quantity+"]";
    }

    @Override
    public int compareTo(Mineral other) {
        return title.compareTo(other.title);
    }
    @Override
    public boolean equals(Object other){
        if(other.getClass().equals(Mineral.class)) {
            return this.compareTo((Mineral)other) == 0;
        }
        return false;
    }
}
