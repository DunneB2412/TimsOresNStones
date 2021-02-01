package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.items.materials.base.ore.OrePieceItem;
import com.timmist24.timsoresnstones.items.materials.base.part.PartItem;
import net.minecraft.item.Item;

import java.util.*;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    //public static final Item.ToolMaterial TOOL_MATERIAL_NONE = EnumHelper.addToolMaterial();

    public static final Item ORE = new OrePieceItem("ore").setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ORE);
    public static final Item PART = new PartItem("part").setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_CMPONENTS);
}
