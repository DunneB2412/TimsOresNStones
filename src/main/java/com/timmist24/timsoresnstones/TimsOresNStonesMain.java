package com.timmist24.timsoresnstones;

import com.timmist24.timsoresnstones.init.ModBlocks;
import com.timmist24.timsoresnstones.init.ModCreativeTabs;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.proxy.CommonProxy;
import com.timmist24.timsoresnstones.util.References;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

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
        CREATIVE_TABS.updateIcone(ModItems.ORE, ModBlocks.COMPRESSED_IRON_ORE);
    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
    }
}
