package com.timmist24.timsoresnstones;

import com.timmist24.timsoresnstones.init.ModBlocks;
import com.timmist24.timsoresnstones.init.ModCreativeTabs;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.mineral.Mineral;
import com.timmist24.timsoresnstones.proxy.CommonProxy;
import com.timmist24.timsoresnstones.util.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.ACCEPTED_VERSIONS)//, dependencies = References.DEPENDENCIES)
public class TimsOresNStonesMain
{
    public static final ModCreativeTabs CREATIVE_TABS = new ModCreativeTabs();
    public static Logger logger;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("tosm strts");
    }


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Mineral.SetUpCollor();
        CREATIVE_TABS.updateIcone(ModItems.ORE,ModItems.PART, ModBlocks.COMPRESSED_IRON_ORE);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((IItemColor) ModItems.ORE, ModItems.ORE,ModItems.PART);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((IBlockColor) ModBlocks.COMPRESSED_IRON_ORE, ModBlocks.COMPRESSED_IRON_ORE);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((IItemColor) ModItems.ORE, ModBlocks.COMPRESSED_IRON_ORE);
        // has to set mineral type here, and mineral color



    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        //LootTableList.
    }
}
