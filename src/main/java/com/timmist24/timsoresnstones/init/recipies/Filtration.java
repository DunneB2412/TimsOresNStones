package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.ore.ItemTosmWithMinerals;
import net.minecraft.item.Item;

public interface Filtration {
    Item[] filter(ItemTosmWithMinerals itemIn, Float strength);
}
