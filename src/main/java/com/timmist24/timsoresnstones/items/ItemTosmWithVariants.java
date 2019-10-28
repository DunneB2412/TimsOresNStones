package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemTosmWithVariants extends ItemTosm{
    private final Enum[] variantsEnum;
    public Color color;

    public ItemTosmWithVariants(String itemName, Enum[] variantsEnum) {
        super(itemName);
        this.variantsEnum = variantsEnum;
        setHasSubtypes(true);
    }
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for(Enum state: variantsEnum) {
                items.add(new ItemStack(this, 1, state.ordinal()));
            }
        }
    }

    @Override
    public void registerModels()// extract from proxy?
    {
        for(Enum state: variantsEnum) {
            String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
            ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
            TimsOresNStonesMain.proxy.registorItemRenderer(this, state.ordinal(), "type="+state.toString(), resourceLocation);
        }

    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + variantsEnum[stack.getMetadata()].toString();
    }
}
