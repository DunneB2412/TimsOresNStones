package com.timmist24.timsoresnstones.items.materials;


import com.google.common.collect.Lists;
import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.items.materials.ore.mineral.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinerlaVariedItem extends Item implements IHasModel, IItemColor {
    private final List<ItemStack> itemStacks = new ArrayList<>();
    private final Enum[] variants;


    public MinerlaVariedItem(String itemName, Enum[] variants) {
        super();
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        this.variants = variants;
        setHasSubtypes(true);
        ModItems.ITEMS.add(this);
        prepareItemstacks();
    }

    private void prepareItemstacks() {
        for(Enum variant: variants) {
            for(int i = 1; i< Mineral.numberOfMinerals(); i++){ // skips the empty dust
                if(Arrays.asList(Mineral.getMineral(i).getType().getDustVariants(variants[0])).contains(variant)) {
                    itemStacks.add(new ItemStack(this, 1, (i*10)+variant.ordinal()));
                }
            }
        }
    }
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            items.addAll(itemStacks);
        }
    }
    @Override
    public void registerModels()// extract from proxy?
    {
        String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
        ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "type="+variants[0].toString(), resourceLocation);
        for(ItemStack itemStack: itemStacks) {
            int varentIndex = itemStack.getMetadata()%10;
            TimsOresNStonesMain.proxy.registorItemRenderer(this, itemStack.getMetadata(), "type="+variants[varentIndex].toString(), resourceLocation);
        }
    }
    @NotNull
    @Override
    public String getUnlocalizedName(@NotNull ItemStack stack)
    {
        int variantNumber = stack.getMetadata()%10;
        return "item." + variants[variantNumber].toString();
    }
    @NotNull
    @Override
    public String getItemStackDisplayName(@NotNull ItemStack stack) {
        String discription = I18n.format("var."+variants[stack.getMetadata()%10].toString());
        String mineralTitle = I18n.format("mineral."+ Mineral.getMineral(stack.getMetadata()/10).title);//get translation
        return mineralTitle+" "+discription; // basic
    }
    @Override
    public String toString(){
        return super.getUnlocalizedName();
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        if (tintIndex != 0) return 0xFFFFFFFF;
        Color[] colors = Mineral.getMineral(stack.getMetadata()/10).colors;
        return colors[colors.length-1].toInt();
    }
}

