package com.timmist24.timsoresnstones.items.materials;


import com.timmist24.timsoresnstones.items.ItemWithMinerals;

public class Dust extends ItemWithMinerals {
    public Dust(String itemName) {
        super(itemName, PureDustVariants.values());
    }


//    @Override
//    public String getItemStackDisplayName(ItemStack stack)
//    {
//        String s = ("" + I18n.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
//        String s1 = "";//EntityList.getTranslationName(getNamedIdFrom(stack));
//
//        if (s1 != null)
//        {
//            s = s + " " + I18n.translateToLocal("entity." + s1 + ".name");
//        }
//
//        return s;
//    }
}

