package com.timmist24.timsoresnstones.items.materials;

import com.timmist24.timsoresnstones.items.ItemTosm;
import com.timmist24.timsoresnstones.items.ItemTosmWithVariants;

public class Dust extends ItemTosmWithVariants {
    public Dust(String itemName) {
        super(itemName, PureDustVariants.values());
    }
}

