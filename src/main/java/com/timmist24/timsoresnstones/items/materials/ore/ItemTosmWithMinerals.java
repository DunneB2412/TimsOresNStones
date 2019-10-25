package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.items.ItemTosmSpecialResource;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.References;

public class ItemTosmWithMinerals extends ItemTosmSpecialResource {
    private static final int MAX_COMPOSITION = References.MAZIMUM_MINERAL;
    public final Mineral[] composition;
    public Color color;

    public ItemTosmWithMinerals(String itemName, Mineral[] composition, String resorceLocation) {
        super(itemName, resorceLocation);
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
