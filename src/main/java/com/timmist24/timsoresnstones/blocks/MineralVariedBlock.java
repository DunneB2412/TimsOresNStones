package com.timmist24.timsoresnstones.blocks;


import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModBlocks;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.mineral.Mineral;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MineralVariedBlock extends Block implements IBlockColor, IHasModel{


    protected final List<ItemStack> itemStacks = new ArrayList<>();


    public MineralVariedBlock(String blockName, Material material) {
        super(material);
        setUnlocalizedName(blockName);
        setRegistryName(blockName);
        this.setDefaultState(this.blockState.getBaseState().withProperty(Mineral.MINERAL_PROPERTY,Mineral.getMineral(0)));

        setCreativeTab(TimsOresNStonesMain.CREATIVE_TABS.TAB_TIMS_BLOCKS);

        this.blockSoundType = SoundType.METAL;

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName()))); //maybe special block item
        prepareItemstacks();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, Mineral.MINERAL_PROPERTY);
    }
    @Override
    public int getMetaFromState(IBlockState state) {
        //return Mineral.indexOf(state.getValue(Mineral.MINERAL_PROPERTY));
        return 0;
    }
//    @Override
//    public IBlockState getStateFromMeta(int meta) {
//        return super.getStateFromMeta(meta);
//    }

    @Override
    public void registerModels(){// extract from proxy?
        Item item = Item.getItemFromBlock(this);
        String resorcePath =this.getUnlocalizedName().replaceAll("\\.","_")+"_variants";
        ResourceLocation resourceLocation= new ResourceLocation("tosm",resorcePath);
        TimsOresNStonesMain.proxy.registorItemRenderer(item, 0, "type="+variantTitle(0), resourceLocation);
        for(ItemStack stack: itemStacks) {
            TimsOresNStonesMain.proxy.registorItemRenderer(item, stack.getMetadata(), "type="+variantTitle(stack), resourceLocation);
        }
    }

    protected void prepareItemstacks() {prepareItemstacks(0);}
    protected final void prepareItemstacks(int var) {
        for(int i = 1; i< Mineral.numberOfMinerals(); i++){ // skips the empty mineral
            itemStacks.add(new ItemStack(this, 1, (i*10)+var));
        }
    }
    public void getSubBlocks( CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.getCreativeTabToDisplayOn().equals(tab)){
            items.addAll(itemStacks);
        }
    }
    public final String variantTitle(ItemStack stack){
        return variantTitle(stack.getMetadata()%10);
    }
    public String variantTitle(int i){
        return this.getUnlocalizedName();
    }


    //    @NotNull
//    @Override
//    public String getUnlocalizedName(@NotNull ItemStack stack)
//    {
//        return "item." + variantTitle(stack);
//    }
//    @NotNull
//    @Override
//    public String getItemStackDisplayName(@NotNull ItemStack stack) {
//        String discription = I18n.format("var."+variantTitle(stack));
//        String mineralTitle = I18n.format("mineral."+ Mineral.getMineral(stack.getMetadata()/10).title);//get translation
//        return mineralTitle+" "+discription; // basic
//    }

    @Override
    public String toString(){
        return super.getUnlocalizedName();
    }

//    @Override
//    public int colorMultiplier(@NotNull ItemStack stack, int tintIndex) {
//        if (tintIndex != 0)return 0xFFFFFFFF;
//        try {
//            Color[] colors = Mineral.getMineral(stack.getMetadata()/10).colors;
//            return colors[colors.length - 1].toInt();
//        }catch (Exception e){
//            TimsOresNStonesMain.logger.catching(e);
//            TimsOresNStonesMain.logger.debug(stack.getDisplayName()+","+tintIndex);
//            return 0xFFFFFFFF;
//        }
//    }

    @Override
    public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return state.getValue(Mineral.MINERAL_PROPERTY).colors[0].toInt();
    }
}
