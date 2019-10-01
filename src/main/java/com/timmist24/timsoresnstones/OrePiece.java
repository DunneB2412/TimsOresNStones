package com.timmist24.timsoresnstones;

import net.minecraft.item.Item;

public class OrePiece extends StonePiece {
    private final String ingotName;

    public OrePiece(String orename, String ingotName) {
        super(orename);
        this.ingotName = ingotName;
    }
}
