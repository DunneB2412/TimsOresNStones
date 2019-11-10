package com.timmist24.timsoresnstones.items.materials;


import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.items.materials.ore.mineral.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Dust extends Item implements IHasModel {
    private final List<ItemStack> itemStacks = new ArrayList<>();


    public Dust(String itemName) {
        super();
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ITEMS);
        setHasSubtypes(true);
        ModItems.ITEMS.add(this);
        prepareItemstacks();
    }

    private void prepareItemstacks() {
        for(DustVariant variant: DustVariant.values()) {
            for(int i = 0; i< Mineral.numberOfMinerals(); i++){
                itemStacks.add(new ItemStack(this, 1, (i*10)+variant.ordinal()));
            }
        }
    }

    public Color getColor(@NotNull ItemStack itemStack){
        return Mineral.getMineral(itemStack.getMetadata()/10).color;
    }
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            items.addAll(itemStacks);
        }
    }
    @Override
    public void registerModels()// extract from proxy?
    {
        for(ItemStack itemStack: itemStacks) {
            int varentIndex = itemStack.getMetadata()%10;
            String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
            ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
            TimsOresNStonesMain.proxy.registorItemRenderer(this, itemStack.getMetadata(), "type="+DustVariant.values()[varentIndex].toString(), resourceLocation);
        }
    }
    @Override
    public String getUnlocalizedName(@NotNull ItemStack stack)
    {
        int variantNumber = stack.getMetadata()%10;
        return "item." + DustVariant.values()[variantNumber].toString();
    }
    @Override
    public String getItemStackDisplayName(@NotNull ItemStack stack) {
        String discription = I18n.format("var."+DustVariant.values()[stack.getMetadata()%10].toString());
        String mineralTitle = I18n.format("mineral."+ Mineral.getMineral(stack.getMetadata()/10).title);//get translation
        return mineralTitle+" "+discription; // basic
    }
    @Override
    public String toString(){
        return super.getUnlocalizedName();
    }

}

