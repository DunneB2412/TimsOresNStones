package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.ore.OrePiece;
import net.minecraft.item.Item;

public interface Filtration {
    Item[] filter(OrePiece itemIn, Float strength);
}
