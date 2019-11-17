package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.items.materials.DustVariant;
import com.timmist24.timsoresnstones.items.materials.MinerlaVariedItem;
import com.timmist24.timsoresnstones.items.materials.PartVariant;
import com.timmist24.timsoresnstones.items.materials.ore.OrePiece;
import net.minecraft.item.Item;

import java.util.*;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    //public static final Item.ToolMaterial TOOL_MATERIAL_NONE = EnumHelper.addToolMaterial();


    public static final Item ORE = new OrePiece("ore");
    public static final Item DUST = new MinerlaVariedItem("dust", DustVariant.values()).setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_ORE );
    public static final Item PART = new MinerlaVariedItem("part", PartVariant.values()).setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_CMPONENTS);
}
