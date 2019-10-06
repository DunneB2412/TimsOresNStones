package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    
    public ItemBase(String itemName) {
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setCreativeTab(CreativeTabs.MATERIALS);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "inventory");
    }
}
