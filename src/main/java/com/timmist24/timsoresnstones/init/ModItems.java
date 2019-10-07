package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.items.ItemBlock;
import com.timmist24.timsoresnstones.items.ItemResorces;
import com.timmist24.timsoresnstones.items.ItemStonePiece;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();


    public static final Item RESORCE_ICON = new ItemResorces("resorce_icon");
    public static final Item BLOCK_ICON = new ItemBlock("block_icon");

    public static final Item STONE_PIECE = new ItemStonePiece("stone_piece");


}
