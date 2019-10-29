package com.timmist24.timsoresnstones.blocks;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModBlocks;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String blockName, Material material) {
        super(material);
        setUnlocalizedName(blockName);
        setRegistryName(blockName);
        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_BLOCKS);
        this.blockSoundType = SoundType.METAL;

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Item item = Item.getItemFromBlock(this);
        TimsOresNStonesMain.proxy.registorItemRenderer(item,0, "normal",item.getRegistryName());
    }
}
