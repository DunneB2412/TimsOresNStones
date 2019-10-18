package com.timmist24.timsoresnstones.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class References {
    public static final String MODID = "tosm";
    public static final String  NAME = "Tim's ores and stones";
    public static final String VERSION = "0.0";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String CLIENT_PROXY_CLASS = "com.timmist24.timsoresnstones.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.timmist24.timsoresnstones.proxy.CommonProxy";

    private static final String[] dusts = {"Dust", "Dust small", "Dust Tiny"};
    private static final String[] components = {"Ingot", "Nugget", "Gear", "Rod", "plate"};
    public static final List<String> METALS_TITALS = new ArrayList<String>(Arrays.asList("Iron", "Gold"));//, "Copper", "Tin", "Silver", "Lead", "Nickel", "Platinum", "Zinc", "Aluminium", "Aluminum", "Alumina", "Chromium", "Chrome", "Uranium", "Iridium", "Osmium", "Bronze", "Steel", "Brass", "Invar", "Electrum", "RefinedIron"));
    public static final List<String> METALS_EXTRAS = new ArrayList<String>(Arrays.asList("Ore Piece", "Compressed Ore Piece","Pulverised Ore"));
    public static final List<String> GEMS_TITALS = new ArrayList<String>(Arrays.asList("Cupronickel", "Constantan", "Ruby", "Sapphire", "Peridot", "Diamond", "Emerald"));
    public static final List<String> GEMS_EXTRAS = new ArrayList<String>(Arrays.asList("raw", "unpure", "cut", "gem"));
    public static final List<String> MINERAL_BLOCKS = new ArrayList<>(Arrays.asList("block", "Block OF Ore Pieces"));
}
