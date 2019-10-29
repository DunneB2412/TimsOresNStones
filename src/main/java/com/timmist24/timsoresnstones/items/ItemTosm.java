package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTosm extends Item implements IHasModel {
    public ItemTosm(String itemName) {
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ITEMS);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "inventory", getRegistryName());
    }
}
