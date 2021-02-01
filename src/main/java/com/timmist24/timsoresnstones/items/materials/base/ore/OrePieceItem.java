package com.timmist24.timsoresnstones.items.materials.base.ore;


import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.items.materials.base.MinerlaVariedItem;
import com.timmist24.timsoresnstones.mineral.Mineral;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;



public class OrePieceItem extends MinerlaVariedItem{
    //sotre volume
    public OrePieceItem(String itemName) {
        super(itemName);
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ORE);
    }

    @Override
    protected void prepareItemstacks() {
        for(OreVariant oreVariant: OreVariant.values()){
            super.prepareItemstacks(oreVariant.ordinal());
        }
    }
    @Override
    public String variantTitle(int i){
        return OreVariant.values()[i].toString();
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
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
//    @Override
//    public String getItemStackDisplayName(@NotNull ItemStack stack) {
//        String discription = I18n.format("var."+variants[stack.getMetadata()%10].toString());
//        String mineralTitle = I18n.format("mineral."+ Mineral.getMineral(stack.getMetadata()/10).title);//get translation
//        return mineralTitle+" "+discription; // basic
//    }


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

}
