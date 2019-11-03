package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.List;

public class ItemWithMinerals extends Item implements IHasModel {

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
        return "item." + variantsEnum[variantNumber].toString();
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String out = "";
        String discription = I18n.format("var."+variantsEnum[stack.getMetadata()%10].toString());
        String mineralTitle = I18n.format("mineral."+ModItems.MINERAL_LIST.get(stack.getMetadata()/10).title);//get translation
        return mineralTitle+" "+discription; // basic
    }

}
