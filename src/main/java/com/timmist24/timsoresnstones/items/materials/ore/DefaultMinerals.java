package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.texturing.Color;

public enum DefaultMinerals {
    IRON(new Mineral("iron",false, 7.87f, new Color("b7410e"))), // as per grams per cm^3
    GOLD(new Mineral("gold",false, 19.3f, new Color("d4af37"))) // as per grams per cm^3
    ;

    private final Mineral mineral;
    DefaultMinerals(Mineral mineral) {
        this.mineral = mineral;
    }
    public Mineral getMineral(){
        return mineral;
    }
}
