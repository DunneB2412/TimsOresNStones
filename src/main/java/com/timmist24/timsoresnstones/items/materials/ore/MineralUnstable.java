package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.DataRetrival;

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
        if(DataRetrival.RANDOM.nextInt(unstability)>10) {
            return DataRetrival.RANDOM.nextBoolean();
        }
        return this.isOilSoluble;
    }
    @Override
    public float getWeightPerUnit(){
        float randomChange = DataRetrival.RANDOM.nextInt(unstability)/3.0f;
        if(DataRetrival.RANDOM.nextBoolean()) {
            return this.weightPerUnit+randomChange;
        }
        return  this.weightPerUnit-randomChange;
    }
}
