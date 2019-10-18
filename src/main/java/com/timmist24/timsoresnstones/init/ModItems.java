package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.items.*;
import com.timmist24.timsoresnstones.util.DataRetrival;
import com.timmist24.timsoresnstones.util.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public final Item STONE_PIECE;
    public final CreativeTabs TAB_TIMS_RESORCES;

    public static final List<Item> ITEMS = new ArrayList<>();

    public ModItems(){
        try {
            DataRetrival.langOut.write("#Mineral Items");
            DataRetrival.langOut.newLine();
            DataRetrival.langOut.write("itemGroup.tabTimsBlocks=Tim's blocks");
            DataRetrival.langOut.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        STONE_PIECE = new ItemStonePiece("stone_piece");

        TAB_TIMS_RESORCES = new CreativeTabs("tabTimsItems") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(STONE_PIECE);
            }
        };
        init();
    }
    public static void init() {
        for (String title : References.METALS_TITALS) {
            for (String subTitle : References.METALS_EXTRAS) {
                String newItemId = DataRetrival.stringArrayToId(new String[]{title, subTitle});
                new ItemBase(newItemId);
            }
        }
    }


    public void setCreativeTab(CreativeTabs newCreativeTab){
        for(Item item: ITEMS){
            item.setCreativeTab(newCreativeTab);
        }
    }
}
