package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.ore.ItemTonsWithMinerals;
import net.minecraft.item.Item;

public interface Filtration {
    Item[] filter(ItemTonsWithMinerals itemIn, Float strength);
}
