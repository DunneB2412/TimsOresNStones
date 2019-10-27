package com.timmist24.timsoresnstones.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
    public void registorItemRenderer(Item item, int meta, String id, ResourceLocation resorceLocation)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(resorceLocation, id));
    }
}
