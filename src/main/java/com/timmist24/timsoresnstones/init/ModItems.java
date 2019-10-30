package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.items.ItemTosm;
import com.timmist24.timsoresnstones.items.materials.Dust;
import com.timmist24.timsoresnstones.items.materials.ore.OrePiece;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    //public static final Item.ToolMaterial TOOL_MATERIAL_NONE = EnumHelper.addToolMaterial();
        static {
        Collection<Block> blocks = GameRegistry.findRegistry(Block.class).getValuesCollection();
        for (Block block: blocks){
            String blockName = block.getUnlocalizedName();
            if(blockName.contains("ore")){
                Item dust = new Dust("dust"+blockName);

            }
        }
    }


    public static final Item STONE_PIECE = new ItemTosm("stone_piece");
    public static final Item ORE = new OrePiece("ore");
    public static final Item DUST = new Dust("dust");
}
