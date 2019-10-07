package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemBlock extends ItemBase {
    static final CreativeTabs tabTimsBlocks = (new CreativeTabs("tabTimsResorces") {

        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.BLOCK_ICON);
        }

    });

    public ItemBlock(String itemName) {
        super(itemName);

        this.setCreativeTab(tabTimsBlocks);
    }
}
