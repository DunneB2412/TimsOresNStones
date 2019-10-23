package com.timmist24.timsoresnstones.blocks;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModBlocks;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBaseFalling extends BlockFalling implements IHasModel {

    public BlockBaseFalling(String blockName, Material material) {
        super(material);
        setUnlocalizedName(blockName);
        setRegistryName(blockName);
        setCreativeTab(BlockBase.tabTimsBlocks);
        setHarvestLevel("Shovle", 2);
        this.blockHardness = (float) 1.5;
        this.blockSoundType = SoundType.GROUND;

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public void registerModels() {
        TimsOresNStonesMain.proxy.registorItemRenderer(Item.getItemFromBlock(this),0, "normal");
    }
}
