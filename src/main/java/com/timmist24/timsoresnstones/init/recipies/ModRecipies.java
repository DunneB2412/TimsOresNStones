package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.base.ore.OrePieceItem;
import net.minecraft.item.Item;

public class ModRecipies {
    private static Item[] filter(OrePieceItem itemin, float power, FiltrationMethods filtrationMethod){
        return filtrationMethod.getFunction().filter(itemin, power);
    }
    public static void init(){
        //FiltrationMethods m = FiltrationMethods.values()[0];
        //Item[] items = filter(new ItemTonsWithMinerals("", new Mineral[]{},""),0, FiltrationMethods.CRUSHING);
        //GameRegistry.addSmelting();
        //GameRegistry.do stuff
        Test test = new Test() {
            @Override
            public Item[] filter(OrePieceItem itemIn, Float strength) {
                return new Item[0];
            }
        };
    }
}
