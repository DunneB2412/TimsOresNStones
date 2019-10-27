package com.timmist24.timsoresnstones.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemTosmWithVariants extends ItemTosm{
    private final Enum[] variantsEnum;
    public ItemTosmWithVariants(String itemName, Enum[] variantsEnum) {
        super(itemName);
        this.variantsEnum = variantsEnum;
    }
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for (Enum object = null; variantsEnum)
            {
                if(object!= null) items.add(new ItemStack(this, 1, object.ordinal()));
            }
        }
    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        int metadata = stack.getMetadata();
        return super.getUnlocalizedName();// + "." + EnumDyeColor.byDyeDamage(i).getUnlocalizedName();
    }
}
