package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InitMineralsMethod { // lots of work to be done in initalising minerals. first impliment assuming it works correctly
    BRUTE_FORCE(() -> {

        TimsOresNStonesMain.logger.warn("Tosm using brute force to retreve minerals");// look for stones sand, gravle, clay, (make a silt) netherstone, endStone (dried variants, bricks ect) add stable memory of minerals somewhere to handle updates to minerals list
        List<Mineral> mineralList = new ArrayList<>();
        List<String> oreDictTites = new ArrayList<>();
        String[] names = OreDictionary.getOreNames();
        for(String name: names){
            if(Pattern.matches("(ingot|gem|crystal)\\w*", name)){
                oreDictTites.add(name);
            }
        }
        Collection<Block> blocks = GameRegistry.findRegistry(Block.class).getValuesCollection();
        String modBeingScanned = "";
        for (Block block: blocks){
            String blockAsString = block.toString();
            Matcher matcher1 = Pattern.compile("Block\\{(\\w+):.*").matcher(blockAsString);
            if(matcher1.matches()){
                if(!matcher1.group(1).equals(modBeingScanned)){
                    modBeingScanned = matcher1.group(1);
                    TimsOresNStonesMain.logger.info("Searching: "+modBeingScanned+" for ore.");
                }
            }
            if(Pattern.matches("Block"+ Util.regexContainsPlus(new String[]{"ore", "resource"}), blockAsString)) {
                List<IBlockState> list = block.getBlockState().getValidStates();
                List<String> foundMinerals = new ArrayList<>();// filyrt out lit variants
                List<String> newMinerals = new ArrayList<>();
                for (IBlockState state : list) {
                    String stateAsString = state.toString();
                    if(Pattern.matches(Util.regexContainsPlus(new String[]{"ore"}), stateAsString)//){
                        && !Pattern.matches(Util.regexContainsPlus(new String[]{"ore_fluid"}), stateAsString)){
                        matcher1 = Pattern.compile(".*\\[type=(\\w+).*").matcher(stateAsString);//thaumcraft uses ore_name, big reactors uses orename,ae2(quartz nor really handled) (not handled here), look for unlocalised name (draconic name is here)
                        Matcher matcher2 = Pattern.compile(".+:(\\w+)").matcher(stateAsString);
                        String mineralTitle = (matcher1.matches() ? matcher1.group(1) : matcher2.matches() ? matcher2.group(1) : stateAsString).replaceAll("_ore", "");
                        //GET HARDNESS FOR MINERAL
                        Mineral newMineral = new Mineral(mineralTitle, false, 10, 0, block, list.indexOf(state));

                        foundMinerals.add(mineralTitle);
                        if(!mineralList.contains(newMineral)) {
                            mineralList.add(newMineral);
                            newMinerals.add(newMineral.toString());
                        }
                    }
                }
                TimsOresNStonesMain.logger.info("Found: "+blockAsString+", containing:"+foundMinerals+" that were ores, of which:"+newMinerals+ (newMinerals.size()>1?" were":" was")+" new.");
            }
        }
        TimsOresNStonesMain.logger.info("Tims instance set up with"+ mineralList+"");
        return mineralList;
    }),
    JSON_FILES(() -> null);

    private IInitMinerals method;
    InitMineralsMethod(IInitMinerals iInitMinerals) {
        method = iInitMinerals;
    }

    public IInitMinerals getInitMinerals() {
        return method;
    }

}
