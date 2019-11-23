package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.texturing.Color;
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
        TimsOresNStonesMain.logger.warn("Tosm using brute force to retreve minerals");
        List<Mineral> mineralList = new ArrayList<>();
        List<String> oreDictTites = new ArrayList<>();
        String[] names = OreDictionary.getOreNames();
        for(String name: names){
            if(Pattern.matches("(ingot|gem|crystal)\\w*", name)){
                oreDictTites.add(name);
            }
        }
        Collection<Block> blocks = GameRegistry.findRegistry(Block.class).getValuesCollection();
        //Collection<Item> items = GameRegistry.findRegistry(Item.class).getValuesCollection();
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
                List<String> foundMinerals = new ArrayList<>();
                List<String> newMinerals = new ArrayList<>();
                for (IBlockState state : list) {
                    String stateAsString = state.toString();
                    if(Pattern.matches(Util.regexContainsPlus(new String[]{"ore"}), stateAsString)){
                        matcher1 = Pattern.compile(".*\\[type=(\\w+).*").matcher(stateAsString);
                        Matcher matcher2 = Pattern.compile(".+:(\\w+)").matcher(stateAsString);
                        String mineralTitle = (matcher1.matches() ? matcher1.group(1) : matcher2.matches() ? matcher2.group(1) : stateAsString).replaceAll("_ore", "");
                        MineralVariant mineralType = MineralVariant.CRYSTAL;//default
                        Color mineralColor = Color.extractColor(Objects.requireNonNull(OreDictionary.getOres(oreDictTites.get(1) + "", true).get(0).getItem().getRegistryName()));//new Color("b7410e");
                        int index = 0;
                        boolean found = false;
                        while(index<oreDictTites.size()&&!found){
                            String dictionaryEntry = oreDictTites.get(index);
                            if(Pattern.matches(Util.regexContainsPlus(new String[]{mineralTitle}), dictionaryEntry)){
                                mineralType = MineralVariant.getFromString(dictionaryEntry.replaceAll(Util.regexFind(mineralTitle), ""));
                                found = true;
                                state.getBlock().getRegistryName();// maby do somehing
                            }
                            index++;
                        }
                        Mineral newMineral;
                        if(mineralType != MineralVariant.METAL && mineralType != MineralVariant.LIQUID){
                            newMineral = new Mineral(mineralTitle+"_alloy", MineralVariant.SYNTETIC, false, 10, 0, mineralColor, Color.combine(mineralColor, new Color("FFFFFF"), 0.20));
                            if(!mineralList.contains(newMineral)) {
                                mineralList.add(newMineral);
                                newMinerals.add(mineralTitle);
                            }
                        }
                        newMineral = new Mineral(mineralTitle, mineralType, false, 10, 0, mineralColor);

                        foundMinerals.add(mineralTitle);
                        if(!mineralList.contains(newMineral)) {
                            mineralList.add(newMineral);
                            newMinerals.add(mineralTitle);
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

//    public static InitMineralsMethod get(String target){
//        switch (target){
//            default:
//                return BRUTE_FORCE;
//            case "json_files":
//                return JSON_FILES;
//        }
//    }
}
