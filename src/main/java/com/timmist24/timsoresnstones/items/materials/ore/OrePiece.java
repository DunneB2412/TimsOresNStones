package com.timmist24.timsoresnstones.items.materials.ore;


import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.items.materials.ore.mineral.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.IHasModel;
import com.timmist24.timsoresnstones.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class OrePiece extends Item implements IHasModel{

    public List<Mineral> composition = new ArrayList<>();
    private Color color;
    public OrePiece(String itemName) {
        super();
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ITEMS);
        setHasSubtypes(true);
        ModItems.ITEMS.add(this);
        updateColor();
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex==1? this.color.toInt(): 0, this);

        this.addPropertyOverride(new IItemPropertyGetter() {

            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return (float)getPowerLevel(stack);
            }

            private Object getPowerLevel(ItemStack stack) {
                NBTTagList entries = stack.getEnchantmentTagList();
                return null;
            }
        });
    }

    private void addPropertyOverride(IItemPropertyGetter iItemPropertyGetter) {
        iItemPropertyGetter.apply()
    }

    public Color getColor(){
        return color;
    }
    private void updateColor(){ // under powered needs workz
        Color newColor = new Color("FFAAAAAA");
        for (Mineral mineral: composition){
            newColor = Color.combine(mineral.color, newColor, (float)mineral.getQuantity()/ References.MAZIMUM_MINERAL);
        }
        color = newColor;
    }
    public void setComposition(){
        throw new UnsupportedOperationException();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            for(OreVariant oreVariant: OreVariant.values()){
                items.add(new ItemStack(this, 1, oreVariant.ordinal()));
            }
        }
    }
    @Override
    public void registerModels()// extract from proxy?
    {
        for(OreVariant oreVariant: OreVariant.values()){
            String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
            ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
            TimsOresNStonesMain.proxy.registorItemRenderer(this, oreVariant.ordinal(), "type="+ oreVariant.toString(), resourceLocation);

        }
    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + OreVariant.values()[stack.getMetadata()].toString();
    }
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String postFix = I18n.format("var."+OreVariant.values()[stack.getMetadata()].toString());
        Mineral max = Mineral.LOWEST;
        int mineralUnits = 0;
        for(Mineral mineral: composition){
            if(mineral.compareTo(max)>0){
                max=mineral;
            }
            mineralUnits += mineral.getQuantity();
        }
        String titleKey;
        if(mineralUnits<8){
            postFix = postFix.replaceAll("(ore)\\ ", "");
            titleKey = "mineral.stone";
        }else {
            titleKey = "mineral."+max.title;
        }
        String prefixKey = "richness."+(mineralUnits<1? "none": mineralUnits<8? "poor": mineralUnits<72? "moderate": mineralUnits<144? "good": mineralUnits<288? "rich":"solid");
        String prefix = I18n.format(prefixKey);
        String title = I18n.format(titleKey);
        String out = prefix+""+title+" "+postFix;
        return out;
    }
    @Override
    public String toString(){
        return super.getUnlocalizedName();
    }
}
