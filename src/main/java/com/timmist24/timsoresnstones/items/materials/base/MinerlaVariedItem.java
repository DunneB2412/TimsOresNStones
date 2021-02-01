package com.timmist24.timsoresnstones.items.materials.base;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.mineral.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class MinerlaVariedItem extends Item implements IHasModel, IItemColor {
    protected final List<ItemStack> itemStacks = new ArrayList<>();

    public MinerlaVariedItem(String itemName) {
        super();
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setHasSubtypes(true);
        ModItems.ITEMS.add(this);
        prepareItemstacks();
    }

    protected void prepareItemstacks() {prepareItemstacks(0);}
    protected final void prepareItemstacks(int var) {
        for(int i = 1; i< Mineral.numberOfMinerals(); i++){ // skips the empty mineral
            itemStacks.add(new ItemStack(this, 1, (i*10)+var));
        }
    }
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            items.addAll(itemStacks);
        }
    }
    public final String variantTitle(ItemStack stack){
        return variantTitle(stack.getMetadata()%10);
    }
    public String variantTitle(int i){
        return this.getUnlocalizedName();
    }
    @Override
    public void registerModels()// extract from proxy?
    {
        String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
        ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "type="+variantTitle(0), resourceLocation);
        for(ItemStack stack: itemStacks) {
            TimsOresNStonesMain.proxy.registorItemRenderer(this, stack.getMetadata(), "type="+variantTitle(stack), resourceLocation);
        }
    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + variantTitle(stack);
    }

    @Override
    public String getItemStackDisplayName( ItemStack stack) {
        String discription = I18n.format("var."+variantTitle(stack));
        String mineralTitle = I18n.format("mineral."+ Mineral.getMineral(stack.getMetadata()/10).title);//get translation
        return mineralTitle+" "+discription; // basic
    }

    @Override
    public String toString(){
        return super.getUnlocalizedName();
    }

    @Override
    public int colorMultiplier( ItemStack stack, int tintIndex) {
        if (tintIndex != 0)return 0xFFFFFFFF;
        try {
            Color[] colors = Mineral.getMineral(stack.getMetadata()/10).colors;
            return colors[colors.length - 1].toInt();
        }catch (Exception e){
            TimsOresNStonesMain.logger.catching(e);
            TimsOresNStonesMain.logger.debug(stack.getDisplayName()+","+tintIndex);
            return 0xFFFFFFFF;
        }
    }
}

