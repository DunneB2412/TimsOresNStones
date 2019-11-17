package com.timmist24.timsoresnstones.items.materials.ore;


import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.items.materials.ore.mineral.Mineral;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;



public class OrePiece extends Item implements IHasModel, IItemColor{


    public OrePiece(String itemName) {
        super();
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ORE);
        setHasSubtypes(true);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
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
        String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
        ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
        for(OreVariant oreVariant: OreVariant.values()){
            TimsOresNStonesMain.proxy.registorItemRenderer(this, oreVariant.ordinal(), "type="+ oreVariant.toString(), resourceLocation);

        }
    }
    @NotNull
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        try {
            return "item." + OreVariant.values()[stack.getMetadata()].toString();
        } catch (Exception e) {
            return super.getUnlocalizedName(stack);
        }
    }
    @NotNull
    @Override
    public String getItemStackDisplayName(@NotNull ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if(stack.getMetadata()>=OreVariant.values().length){
            return super.getItemStackDisplayName(stack);
        }
        int mineralUnits = getMineralUnits(nbt);
        Mineral primaryMineral = getPrimaryMineral(nbt);

        String postFix = I18n.format("var."+OreVariant.values()[stack.getMetadata()].toString());
        String titleKey;
        if(mineralUnits<8){
            postFix = postFix.replaceAll("(ore )", "");
            titleKey = "mineral.stone";
        }else {
            titleKey = "mineral."+primaryMineral.title;
        }
        String prefixKey = "richness."+(mineralUnits<1? "none": mineralUnits<8? "poor": mineralUnits<72? "moderate": mineralUnits<144? "good": mineralUnits<288? "rich":"solid");
        String prefix = I18n.format(prefixKey);
        String title = I18n.format(titleKey);
        String displayName = ((prefix.length()==0? "":prefix+" ")+title+" "+postFix).toLowerCase();
        return displayName.replaceFirst(".", (displayName.charAt(0)+"").toUpperCase());
    }
    @Override
    public String toString(){
        return super.getUnlocalizedName();
    }


    private int getMineralUnits(NBTTagCompound nbt){
        if(nbt == null || !(nbt.hasKey("total"))) {
            return 0;
        }
        return nbt.getInteger("total");
    }
    private Mineral getPrimaryMineral(NBTTagCompound nbt){
        if(nbt == null || !(nbt.hasKey("minerals"))) {
            return Mineral.getMineral(0);
        }
        return Mineral.getMineral(nbt.getCompoundTag(nbt.getIntArray("minerals")[0]+"pos").getInteger("MineralsIndex"));
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        if (tintIndex != 0) return 0xFFFFFFFF;
        return getPrimaryMineral(stack.getTagCompound()).color.toInt();
    }
}
