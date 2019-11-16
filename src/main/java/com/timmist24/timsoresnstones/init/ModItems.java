package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.items.materials.Dust;
import com.timmist24.timsoresnstones.items.materials.ore.OrePiece;
import net.minecraft.item.Item;

import java.util.*;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    //public static final Item.ToolMaterial TOOL_MATERIAL_NONE = EnumHelper.addToolMaterial();


    public static final Item ORE = new OrePiece("ore");
    public static final Item DUST = new Dust("dust");
}
