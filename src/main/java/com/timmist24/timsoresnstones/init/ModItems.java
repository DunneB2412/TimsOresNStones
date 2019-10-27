package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.items.ItemTosm;
import com.timmist24.timsoresnstones.items.materials.ore.Mineral;
import com.timmist24.timsoresnstones.items.materials.ore.OrePiece;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    //public static final Item.ToolMaterial TOOL_MATERIAL_NONE = EnumHelper.addToolMaterial();

    public static final Item STONE_PIECE = new ItemTosm("stone_piece");
    public static final Item TEST = new OrePiece("test",new Mineral[]{});
}
