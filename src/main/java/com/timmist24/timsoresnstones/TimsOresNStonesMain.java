package com.timmist24.timsoresnstones;

import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.proxy.CommonProxy;
import com.timmist24.timsoresnstones.util.DataRetrival;
import com.timmist24.timsoresnstones.util.References;
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
    private static Boolean dataWorks = DataRetrival.outputsInit();
    public static ModItems modItems = new ModItems(dataWorks);
    private static Boolean dataSaves = DataRetrival.outputsClose();

    private static Logger logger;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        modItems.setCreativeTab(modItems.TAB_TIMS_RESORCES);
        logger = event.getModLog();
    }


    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        LootTableList.register(new ResourceLocation("tosm", "ore_drops.json"));
    }
}
