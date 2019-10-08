package com.timmist24.timsoresnstones.blocks;

import com.timmist24.timsoresnstones.init.ModBlocks;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockBase extends Block implements IHasModel {
    static final CreativeTabs tabTimsBlocks = (new CreativeTabs("tabTimsResorces") {

        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.BLOCK_ICON);
        } // temp icon

    });
    public BlockBase(String blockName, Material material) {
        super(material);
        setUnlocalizedName(blockName);
        setRegistryName(blockName);
        setCreativeTab(tabTimsBlocks);

        ModBlocks.BLOCKS.add(this);



    }

    @Override
    public void registerModels() {

    }
}
