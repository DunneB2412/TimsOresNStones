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

    public ModItems(Boolean write){
        STONE_PIECE = new ItemStonePiece("stone_piece");

        TAB_TIMS_RESORCES = new CreativeTabs("tabTimsItems") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(STONE_PIECE);
            }
        };
        if ((write)) {
            initWrite();
        } else {
            init();
        }
    }
    public void setCreativeTab(CreativeTabs newCreativeTab){
        for(Item item: ITEMS){
            item.setCreativeTab(newCreativeTab);
        }
    }

    public static void initWrite() {
        try {
            DataRetrival.langOut.write("//Mineral Items");
            DataRetrival.langOut.newLine();
            for (String title : References.METALS_TITALS) {
                for (String subTitle : References.METALS_EXTRAS) {
                    String newItemId = DataRetrival.getId(new String[]{title, subTitle});
                    DataRetrival.langOut.write("item." + newItemId + ".name=" + title + " " + subTitle);
                    DataRetrival.langOut.newLine();
                    new ItemBase(newItemId);
                }
            }
            System.out.println("yeet");

        } catch (IOException e) {
            init();
        }

    }
    public static void init() {
        for (String title : References.METALS_TITALS) {
            for (String subTitle : References.METALS_EXTRAS) {
                String newItemId = DataRetrival.getId(new String[]{title, subTitle});
                new ItemBase(newItemId);
            }
        }
    }
}
