package com.timmist24.timsoresnstones.blocks;

import com.timmist24.timsoresnstones.items.materials.ore.mineral.Mineral;
import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockOfOrePieces extends BlockBaseFalling implements IBlockColor {
    private final List<Mineral> minerals = new ArrayList<Mineral>();
    private Color color;

    public BlockOfOrePieces(String blockName, Material material) {
        super(blockName, material);
    }
    public void setMinerals(List materials){
        Color newColor = new Color("ff000000");
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return Color.random(new Random()).toInt();
    }
}
