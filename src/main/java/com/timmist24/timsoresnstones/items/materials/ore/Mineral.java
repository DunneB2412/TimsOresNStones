package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.DataRetrival;

public class Mineral {
    private static final Color DEFAULT_COLOR = new Color("b7410e"); // looks like iron
    private static final int DEFAULT_RANDOM_LIM = 45;
    public final String title;
    protected final boolean isOilSoluble;
    protected float weightPerUnit;
    private int quantity; // 1 = 1 tiny
    public final Color color;

    public Mineral(String title, boolean isOilSoluble, float weightPerUnit, Color color) {
        this(title, isOilSoluble, weightPerUnit, color, DataRetrival.RANDOM.nextInt(DEFAULT_RANDOM_LIM));
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
}
