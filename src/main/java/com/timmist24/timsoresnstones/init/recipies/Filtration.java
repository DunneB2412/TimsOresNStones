package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.base.ore.OrePieceItem;
import net.minecraft.item.Item;

public interface Filtration {
    Item[] filter(OrePieceItem itemIn, Float strength);
}
