package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.items.ItemTosm;
import com.timmist24.timsoresnstones.items.materials.Dust;
import com.timmist24.timsoresnstones.items.materials.ore.Mineral;
import com.timmist24.timsoresnstones.items.materials.ore.OrePiece;
import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<>();
    private static final List<Mineral> MINERAL_LIST = new ArrayList<>();


    //public static final Item.ToolMaterial TOOL_MATERIAL_NONE = EnumHelper.addToolMaterial();
    static {
        //String [] orenames = OreDictionary.getOreNames();
        Collection<Block> blocks = GameRegistry.findRegistry(Block.class).getValuesCollection();
        String modBeingScanned = "";
        for (Block block: blocks){
            String blockAsString = block.toString();
            Matcher matcher1 = Pattern.compile("Block\\{(\\w+):.*\\}").matcher(blockAsString);
            if(matcher1.matches()){
                if(!matcher1.group(1).equals(modBeingScanned)){
                    modBeingScanned = matcher1.group(1);
                    TimsOresNStonesMain.logger.info("Searching: "+modBeingScanned+" for ore");
                }
            }
            if(blockAsString.contains("ore")||blockAsString.contains("resource")) {
                List<IBlockState> list = block.getBlockState().getValidStates();
                for (IBlockState state : list) {
                    String stateAsString = state.toString();
                    matcher1 = Pattern.compile(".*\\[type=(\\w+)\\]").matcher(stateAsString);
                    Matcher matcher2 = Pattern.compile(".+:(\\w+)_ore").matcher(stateAsString);
                    String mineralTitle = matcher1.matches() ? matcher1.group(1).replaceAll("_ore", "") : matcher2.matches() ? matcher2.group(1) : stateAsString;
                    Mineral newMineral = new Mineral(mineralTitle, false, 10, new Color("000000"), 1);
                    if (!MINERAL_LIST.contains(newMineral)) {
                        MINERAL_LIST.add(newMineral);
                        new Dust(mineralTitle + "dust");
                        block.getRegistryName();
                    }
                }
            }
        }
        TimsOresNStonesMain.logger.info(MINERAL_LIST);
    }


    public static final Item STONE_PIECE = new ItemTosm("stone_piece");
    public static final Item ORE = new OrePiece("ore");
    public static final Item DUST = new Dust("dust");
}
