package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.texturing.Color;
import com.timmist24.timsoresnstones.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mineral implements Comparable<Mineral>{
    private static final List<Mineral> MINERALS = new ArrayList<>();  // turn into an unmutable array at some point. maby
    private static final Random RANDOM = new Random();
    private static final float WEIGHT_FACTOR = 0.33f;
    private static final int UNSTABILITY_OIL_THRESHOLD = 30;
    static {
        MINERALS.add(new Mineral("empty", MineralVariant.METAL, false, 0.0f, 0, new Color("bebebe")));
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
                List<String> foundMinerals = new ArrayList<>();
                List<String> newMinerals = new ArrayList<>();
                for (IBlockState state : list) {
                    String stateAsString = state.toString();
                    if(Pattern.matches(Util.regexContainsPlus(new String[]{"ore"}), stateAsString)){
                        matcher1 = Pattern.compile(".*\\[type=(\\w+).*").matcher(stateAsString);
                        Matcher matcher2 = Pattern.compile(".+:(\\w+)").matcher(stateAsString);
                        String mineralTitle = (matcher1.matches() ? matcher1.group(1) : matcher2.matches() ? matcher2.group(1) : stateAsString).replaceAll("_ore", "");
                        MineralVariant mineralType = MineralVariant.CRYSTAL;//default
                        Color mineralColor = Color.random(new Random());//new Color("b7410e");
                        int index = 0;
                        Boolean found = false;
                        while(index<oreDictTites.size()&&!found){
                            String dictionaryEntry = oreDictTites.get(index);
                            if(Pattern.matches(Util.regexContainsPlus(new String[]{mineralTitle}), dictionaryEntry)){
                                mineralType = MineralVariant.getFromString(dictionaryEntry.replaceAll(Util.regexFind(mineralTitle), ""));
                                found = true;
                                state.getBlock().getRegistryName();// maby do somehing
                            }
                            index++;
                        }
                        Mineral newMineral = new Mineral(mineralTitle, mineralType, false, 10, 0, mineralColor);
                        foundMinerals.add(mineralTitle);
                        if(!MINERALS.contains(newMineral)) {
                            MINERALS.add(newMineral);
                            newMinerals.add(mineralTitle);
                        }
                    }
                }
                TimsOresNStonesMain.logger.info("Found: "+blockAsString+", containing:"+foundMinerals+" that were ores, of which:"+newMinerals+ (newMinerals.size()>1?" were":" was")+" new.");
            }
        }
        TimsOresNStonesMain.logger.info("Tims instance set up with"+ MINERALS+"");
    }
    public static Mineral getMineral(int i) {
        return MINERALS.get(i);
    }
    public static int numberOfMinerals(){
        return MINERALS.size();
    }







    public final String title;
    public final Color color;
    private final int unstability;
    private final MineralVariant type;
    private final boolean isOilSoluble;
    private final float weightPerUnit;
    private Mineral(String title, MineralVariant type, boolean isOilSoluble, float weightPerUnit, int unstability, Color color) {
        this.title = title;
        this.type = type;
        this.unstability = unstability;
        this.isOilSoluble = isOilSoluble;
        this.weightPerUnit = weightPerUnit;
        this.color = color;
    }
    public boolean isOilSoluble() {
        if(unstability>UNSTABILITY_OIL_THRESHOLD){
            return RANDOM.nextBoolean();
        }
        return isOilSoluble;
    }
    public float getWeightPerUnit() {
        float randomChange = RANDOM.nextFloat()*WEIGHT_FACTOR;
        if(RANDOM.nextBoolean()){
            return weightPerUnit+randomChange;
        }
        return weightPerUnit- randomChange;
    }
    public MineralVariant getType() {
        return type;
    }
    @Override
    public String toString(){
       return "Mineral:"+title+"<"+type+">";
    }
    @Override
    public int compareTo(Mineral other) {
        return title.compareTo(other.title);
    }
    @Override
    public boolean equals(Object other){
        if(other.getClass().equals(Mineral.class)) {
            return title.equals(((Mineral)other).title);
        }
        return false;
    }
}
