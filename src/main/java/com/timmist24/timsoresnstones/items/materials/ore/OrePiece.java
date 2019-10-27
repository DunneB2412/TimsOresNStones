package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.items.materials.ItemTosmWithMinerals;
import net.minecraft.util.ResourceLocation;

public class OrePiece extends ItemTosmWithMinerals {

    public OrePiece(String itemName, Mineral[] composition) {
        super(itemName, composition, OrePieceStates.size());
        this.setHasSubtypes(true);
    }
    @Override
    public void registerModels()
    {
        for(OrePieceStates state: OrePieceStates.values()) {
            ResourceLocation resourceLocation= new ResourceLocation("tosm","item_ore_variants");
            TimsOresNStonesMain.proxy.registorItemRenderer(this, state.getMeta(), "type="+state.toString(), resourceLocation);
        }

    }
    
    public String getItemStackDisplayName(ItemStack stack)
    {
        String s = ("" + I18n.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        String s1 = EntityList.getTranslationName(getNamedIdFrom(stack));

        if (s1 != null)
        {
            s = s + " " + I18n.translateToLocal("entity." + s1 + ".name");
        }

        return s;
    }
}
