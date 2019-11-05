package com.timmist24.timsoresnstones.init;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
    public final CreativeTabs TAB_TIMS_ITEMS;
    public final CreativeTabs TAB_TIMS_BLOCKS;
    private Item timsItemsIcon;
    private Block timsBlocksIcon;
    public ModCreativeTabs(){
        TAB_TIMS_ITEMS = new CreativeTabs("tims_items") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(timsItemsIcon);
            }
        };
        TAB_TIMS_BLOCKS = new CreativeTabs("tims_items") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(timsBlocksIcon);
            }
        };
    }
    public void updateIcone(Item item, Block block){
        timsItemsIcon = item;
        timsBlocksIcon = block;
    }
}
