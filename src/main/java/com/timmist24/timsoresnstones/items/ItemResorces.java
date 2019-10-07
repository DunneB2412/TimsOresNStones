package com.timmist24.timsoresnstones.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemResorces extends ItemBase {
    static final CreativeTabs tabtimsResorces = (new CreativeTabs("tabTimsResorces") {

        @Override
        public ItemStack getTabIconItem() {
                return null;
        }

    });
    public ItemResorces(String itemName) {
        super(itemName);

        this.setCreativeTab(tabtimsResorces);
    }
}
