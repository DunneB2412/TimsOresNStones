package com.timmist24.timsoresnstones.blocks;

import com.timmist24.timsoresnstones.items.materials.ore.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockOfOrePieces extends BlockBaseFalling{
    private final List<Mineral> minerals = new ArrayList<Mineral>();
    private Color color;

    public BlockOfOrePieces(String blockName, Material material) {
        super(blockName, material);
    }
    public void setMinerals(List materials){
        Color newColor = new Color("ff000000");
    }

}
