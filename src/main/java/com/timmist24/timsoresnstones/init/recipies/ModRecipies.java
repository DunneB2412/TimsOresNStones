package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.ItemTosmWithMinerals;
import net.minecraft.item.Item;

public class ModRecipies {
    private static Item[] filter(ItemTosmWithMinerals itemin, float power, FiltrationMethods filtrationMethod){
        return filtrationMethod.getFunction().filter(itemin, power);
    }
    public static void init(){
        //Item[] items = filter(new ItemTonsWithMinerals("", new Mineral[]{},""),0, FiltrationMethods.CRUSHING);
        //GameRegistry.addSmelting();
        //GameRegistry.do stuff
    }
}
