package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.items.ItemTosmWithVariants;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.References;

public class OrePiece extends ItemTosmWithVariants {
    private static final int MAX_COMPOSITION = References.MAZIMUM_MINERAL;
    public Mineral[] composition;
    public OrePiece(String itemName) {
        super(itemName, OrePieceVariants.values());
        updateColor();
    }

    private void updateColor(){
        Color newColor = new Color("00000000");
        for (Mineral mineral: composition){
            newColor = Color.combine(mineral.color, newColor, (float)mineral.getQuantity()/MAX_COMPOSITION);
        }
    }
    
    public String getItemStackDisplayName(ItemStack stack)
    {
        String s = ("" + I18n.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        String s1 = EntityList.getTranslationName(getNamedIdFrom(stack));

        if (s1 != null)
        {
            s = s + " " + I18n.translateToLocal("entity." + s1 + ".name");
        }

        return s;
    }
}
