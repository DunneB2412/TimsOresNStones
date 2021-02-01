package com.timmist24.timsoresnstones.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockOfOrePieces extends BlockBaseFalling implements IBlockColor {

    public BlockOfOrePieces(String blockName, Material material) {
        super(blockName, material);
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return 0xff55ff08;
    }
}
