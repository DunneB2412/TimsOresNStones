package com.timmist24.timsoresnstones.init;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.items.materials.Dust;
import com.timmist24.timsoresnstones.items.materials.ore.mineral.Mineral;
import com.timmist24.timsoresnstones.items.materials.ore.OrePiece;
import com.timmist24.timsoresnstones.items.materials.ore.mineral.MineralVariant;
import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    private static final List<String> ORE_DICT_TITTLAL = new ArrayList<>();


    //public static final Item.ToolMaterial TOOL_MATERIAL_NONE = EnumHelper.addToolMaterial();
    static {
        String[] names = OreDictionary.getOreNames();
        for(String name: names){
            if(Pattern.matches("(ingot|gem|crystal)\\w*", name)){
                ORE_DICT_TITTLAL.add(name);
                List<ItemStack> stacks = OreDictionary.getOres(name);
            }
        }
        Collection<Block> blocks = GameRegistry.findRegistry(Block.class).getValuesCollection();
        String modBeingScanned = "";
        for (Block block: blocks){
            String blockAsString = block.toString();
            Matcher matcher1 = Pattern.compile("Block\\{(\\w+):.*\\}").matcher(blockAsString);
            if(matcher1.matches()){
                if(!matcher1.group(1).equals(modBeingScanned)){
                    modBeingScanned = matcher1.group(1);
                    TimsOresNStonesMain.logger.info("Searching: "+modBeingScanned+" for ore.");
                }
            }
            if(Pattern.matches("Block.*((?:(?:[^a-zA-Z]o|O)re)|(?:(?:[^a-zA-Z]r|R)esource)).*", blockAsString)) {
                List<IBlockState> list = block.getBlockState().getValidStates();
                List<String> foundMinerals = new ArrayList<>();
                List<String> newMinerals = new ArrayList<>();
                for (IBlockState state : list) {
                    String stateAsString = state.toString();
                    if(Pattern.matches(".*((?:[^a-zA-Z]o|O)re).*", stateAsString)){
                        matcher1 = Pattern.compile(".*\\[type=(\\w+)\\]").matcher(stateAsString);
                        Matcher matcher2 = Pattern.compile(".+:(\\w+)").matcher(stateAsString);
                        String mineralTitle = (matcher1.matches() ? matcher1.group(1) : matcher2.matches() ? matcher2.group(1) : stateAsString).replaceAll("_ore", "");
                        Mineral newMineral = new Mineral(mineralTitle, MineralVariant.METAL, false, 10, 0, new Color("000000"));
                        foundMinerals.add(mineralTitle);
                        newMinerals.add(Mineral.add(newMineral));

                    }
                }
                TimsOresNStonesMain.logger.info("Found: "+blockAsString+", containing:"+foundMinerals+" that were ores, of which:"+newMinerals+ (newMinerals.size()>1?" were":" was")+" new.");
            }
        }
        TimsOresNStonesMain.logger.info("Tims instance set up with"+ Mineral.getMinerals()+"");
    }

    public static final Item ORE = new OrePiece("ore");
    public static final Item DUST = new Dust("dust");
}
