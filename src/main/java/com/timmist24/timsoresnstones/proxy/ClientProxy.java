package com.timmist24.timsoresnstones.proxy;


import com.timmist24.timsoresnstones.items.ItemTosm;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
    public void registorItemRenderer(ItemTosm item, int meta, String id, String resorceLocation)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(resorceLocation, id));
    }
}
