package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();


    //public static final Item BLOCK_ICON = new ItemBase("block_icon");
    //public static final Item IRON_TEST = new ItemBlock("iron_test");

    public static final Item STONE_PIECE = new ItemStonePiece("stone_piece");
    public static final Item IRON_ORE_PIECE = new ItemOrePiece("iron_ore_piece", "iron");


}
