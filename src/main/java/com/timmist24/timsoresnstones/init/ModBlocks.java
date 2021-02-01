package com.timmist24.timsoresnstones.init;


import com.timmist24.timsoresnstones.blocks.BlockOfOrePieces;
import com.timmist24.timsoresnstones.blocks.MineralVariedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block COMPRESSED_IRON_ORE = new BlockOfOrePieces("block_iron_ore", Material.SAND);
    //public static final Block MINERAL_BLOCK = new MineralVariedBlock("block_iron_ore", Material.IRON);
}
