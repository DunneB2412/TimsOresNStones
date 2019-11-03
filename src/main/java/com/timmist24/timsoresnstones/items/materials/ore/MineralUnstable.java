package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.texturing.Color;

/**
 * distabalised redstone, ect
 */
public class MineralUnstable extends Mineral {
    private final int unstability;// bigger is more unstable

    public MineralUnstable(String title, boolean isOilSoluble, float weightPerUnit, Color color, int unstability) {
        super(title, isOilSoluble, weightPerUnit, color);
        this.unstability = unstability;
    }
    public MineralUnstable(String title, boolean isOilSoluble, float weightPerUnit, Color color, int quantity, int unstability) {
        super(title, isOilSoluble, weightPerUnit, color, quantity);
        this.unstability = unstability;
    }
    @Override
    public boolean isOilSoluble(){
        if(RANDOM.nextInt(unstability)>10) {
            return RANDOM.nextBoolean();
        }
        return super.isOilSoluble();
    }
    @Override
    public float getWeightPerUnit(){
        float randomChange = RANDOM.nextInt(unstability)/3.0f;
        if(RANDOM.nextBoolean()) {
            return super.getWeightPerUnit()+randomChange;
        }
        return  super.getWeightPerUnit()-randomChange;
    }
}
