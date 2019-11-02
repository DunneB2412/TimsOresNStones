package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.items.materials.ore.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemWithMinerals extends Item implements IHasModel {
    private final Enum[] variantsEnum;
    public Color color;

    public ItemWithMinerals(String itemName, Enum[] variantsEnum) {
        super();
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        Matcher matcher = Pattern.compile("[\\w]+[^a-zA-Z]([a-zA-Z]+)").matcher(itemName);
        if(matcher.matches()){
            setUnlocalizedName(matcher.group(1));
        }
        else{
            TimsOresNStonesMain.logger.info("there is some wierd stuff happening here with item:"+itemName);
        }
        this.variantsEnum = variantsEnum;
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ITEMS);
        setHasSubtypes(true);
        ModItems.ITEMS.add(this);
    }
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for(Enum state: variantsEnum) {
                for(Mineral mineral:ModItems.MINERAL_LIST){
                    int index = ModItems.MINERAL_LIST.indexOf(mineral);
                    items.add(new ItemStack(this, 1, (index*10)+state.ordinal()));
                }
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
