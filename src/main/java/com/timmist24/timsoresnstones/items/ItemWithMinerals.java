package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.IHasModel;
import com.timmist24.timsoresnstones.util.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemWithMinerals extends Item implements IHasModel {
    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(References.NESTING_DIRECTORY_PATH+"\\resources\\assets\\tosm\\lang\\en_us.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Enum[] variantsEnum;
    private final List<ItemStack> itemVariants = new ArrayList<>();
    public Color color;

    public ItemWithMinerals(String itemName, Enum[] variantsEnum) {
        super();
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        this.variantsEnum = variantsEnum;
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ITEMS);
        setHasSubtypes(true);
        ModItems.ITEMS.add(this);
        initaliseSubItems();
    }
    private void initaliseSubItems(){
        for(Enum state: variantsEnum) {
            for(int i =0; i<ModItems.MINERAL_LIST.size(); i++){
                itemVariants.add(new ItemStack(this, 1, (i*10)+state.ordinal()));
            }
        }
    }
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            items.addAll(itemVariants);
        }
    }

    @Override
    public void registerModels()// extract from proxy?
    {
        TimsOresNStonesMain.logger.info(itemVariants);
        int enumIndex = -1;
        for(ItemStack variant: itemVariants) {
            String number = variant.getMetadata()+"";
            number = (number.charAt(number.length()-1))+"";
            int variantNumber = Integer.parseInt(number);
            if(enumIndex!=variantNumber){
                enumIndex=variantNumber;
            }
            String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
            ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
                     TimsOresNStonesMain.proxy.registorItemRenderer(this, variant.getMetadata(), "type="+variantsEnum[variantNumber].toString(), resourceLocation);
        }

    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        int variantNumber = stack.getMetadata()%10;
        int mineralIndex = stack.getMetadata()/10;
        try{
            writer.write("item."+ ModItems.MINERAL_LIST.get(mineralIndex).title + "_" + variantsEnum[variantNumber].toString()+"="+ModItems.MINERAL_LIST.get(mineralIndex).title+" "+variantsEnum[variantNumber].toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "item."+ ModItems.MINERAL_LIST.get(mineralIndex).title + "_" + variantsEnum[variantNumber].toString();
    }
    public static void closeWriter(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
