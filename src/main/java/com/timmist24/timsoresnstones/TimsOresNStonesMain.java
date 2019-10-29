package com.timmist24.timsoresnstones;

import com.timmist24.timsoresnstones.init.ModBlocks;
import com.timmist24.timsoresnstones.init.ModCreativeTabs;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.proxy.CommonProxy;
import com.timmist24.timsoresnstones.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.ACCEPTED_VERSIONS)
public class TimsOresNStonesMain
{
    public static final ModCreativeTabs CREATIVE_TABS = new ModCreativeTabs();
    private static Logger logger;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        CREATIVE_TABS.updateIcone(ModItems.STONE_PIECE, ModBlocks.COMPRESSED_IRON_ORE);
    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        LootTableList.register(new ResourceLocation("tosm", "ore_drops.json"));
    }
}
