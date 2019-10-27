package com.timmist24.timsoresnstones.items.materials;

import com.timmist24.timsoresnstones.items.ItemTosmWithVariants;
import com.timmist24.timsoresnstones.items.materials.ore.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.References;

public class ItemTosmWithMinerals extends ItemTosmWithVariants {
    private static final int MAX_COMPOSITION = References.MAZIMUM_MINERAL;
    public final Mineral[] composition;
    public Color color;

    public ItemTosmWithMinerals(String itemName, Mineral[] composition, int cariantCount) {
        super(itemName, cariantCount);
        this.composition = composition;
        updateColor();
    }
    private void updateColor(){
        Color newColor = new Color("00000000");
        for (Mineral mineral: composition){
            newColor = Color.combine(mineral.color, newColor, (float)mineral.getQuantity()/MAX_COMPOSITION);
        }
    }

}
