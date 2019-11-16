package com.timmist24.timsoresnstones.init;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
    public final CreativeTabs TAB_TIMS_ORE;
    public final CreativeTabs TAB_ITEMS_CMONENTS;
    public final CreativeTabs TAB_TIMS_BLOCKS;

    private Item timsItemsIcon;
    private Item timsComponentsIcon;
    private Block timsBlocksIcon;
    public ModCreativeTabs(){
        TAB_TIMS_ORE = new CreativeTabs("tims_ores") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(timsItemsIcon);
            }
        };
        TAB_ITEMS_CMONENTS = new CreativeTabs("tims_components") {
            @Override
            public ItemStack getTabIconItem() { return new ItemStack(timsComponentsIcon); }
        };
        TAB_TIMS_BLOCKS = new CreativeTabs("tims_items") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(timsBlocksIcon);
            }
        };
    }
    public void updateIcone(Item item, Item item2, Block block){
        timsItemsIcon = item;
        timsComponentsIcon = item2;
        timsBlocksIcon = block;

    }
}
