package com.timmist24.timsoresnstones.items;

public class ItemOrePiece extends ItemStonePiece {
    private final String ingotName;

    public ItemOrePiece(String orename, String ingotName) {
        super(orename);
        this.ingotName = ingotName;
    }
}
