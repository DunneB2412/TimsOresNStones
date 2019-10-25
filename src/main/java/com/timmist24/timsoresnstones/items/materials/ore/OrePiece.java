package com.timmist24.timsoresnstones.items.materials.ore;

public class OrePiece extends ItemTosmWithMinerals {


    public OrePiece(String itemName, Mineral[] composition) {
        super(itemName, composition, "item.ore_piece");
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
