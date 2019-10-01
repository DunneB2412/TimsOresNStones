package com.timmist24.timsoresnstones;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.ObjectUtils;

public class StonePiece extends Item {
    static final CreativeTabs timsResorces = (new CreativeTabs() {
        @Override
        public ItemStack getTabIconItem() {
            return ItemStack(Null);
        }
    })
    private final String name;

    public StonePiece(String name){
        super();
        this.name = name;
        //setCreativeTab("Tim's resorces");
    }
}
