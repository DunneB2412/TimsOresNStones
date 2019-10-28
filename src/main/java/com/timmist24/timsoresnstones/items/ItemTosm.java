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

//    private static final CreativeTabs TAB_TIMS_RESORCES = new CreativeTabs("tims_items") {
//        @Override
//        public ItemStack getTabIconItem() {
//            return new ItemStack(ModItems.STONE_PIECE);
//        }
//    };

    public ItemTosm(String itemName) {
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        //setCreativeTab(TAB_TIMS_RESORCES);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "inventory", getRegistryName());
    }
}
