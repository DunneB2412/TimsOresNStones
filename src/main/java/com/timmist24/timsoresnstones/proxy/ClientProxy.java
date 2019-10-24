package com.timmist24.timsoresnstones.proxy;


import com.timmist24.timsoresnstones.items.ItemTons;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
    public void registorItemRenderer(ItemTons item, int meta, String id, String resorceLocation)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(resorceLocation, id));
    }
}
