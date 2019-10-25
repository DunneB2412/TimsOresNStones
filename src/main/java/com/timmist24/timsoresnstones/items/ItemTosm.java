package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.item.Item;

public class ItemTosm extends Item implements IHasModel {

    public ItemTosm(String itemName) {
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setCreativeTab(ModItems.TAB_TIMS_RESORCES); //default
        ModItems.ITEMS.add(this);
    }
    @Override
    public void registerModels()
    {
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "inventory", getUnlocalizedName());
    }
}
