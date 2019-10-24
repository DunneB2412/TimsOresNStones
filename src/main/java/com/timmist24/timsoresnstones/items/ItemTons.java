package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;
import com.timmist24.timsoresnstones.init.ModItems;
import com.timmist24.timsoresnstones.util.DataRetrival;
import com.timmist24.timsoresnstones.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.io.IOException;

public class ItemTons extends Item implements IHasModel {

    public ItemTons(String itemName) {
        setUnlocalizedName(itemName);
        setRegistryName(itemName);
        setCreativeTab(CreativeTabs.MATERIALS); //default

        boolean langAdded = writetoLanguage();

        if(langAdded)ModItems.ITEMS.add(this);
    }
    private boolean writetoLanguage(){
        try {
            DataRetrival.langOut.write(this.getUnlocalizedName() + ".name=" +DataRetrival.idToEnglishName(this.getUnlocalizedName()));
            DataRetrival.langOut.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public void registerModels()
    {
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "inventory", getUnlocalizedName());
    }
}
