package com.timmist24.timsoresnstones.util;

public class OrePiece extends StonePiece {
    private final String ingotName;

    public OrePiece(String orename, String ingotName) {
        super(orename);
        this.ingotName = ingotName;
    }
}
