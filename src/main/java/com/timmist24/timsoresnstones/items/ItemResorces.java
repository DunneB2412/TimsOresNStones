package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemResorces extends ItemBase {
    static final CreativeTabs tabTimsResorces = (new CreativeTabs("tabTimsResorces") {

        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.STONE_PIECE);
        }

    });
    public ItemResorces(String itemName) {
        super(itemName);

        this.setCreativeTab(tabTimsResorces);
    }
}
