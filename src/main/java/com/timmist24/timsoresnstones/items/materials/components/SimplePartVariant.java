package com.timmist24.timsoresnstones.items.materials.components;

public enum SimplePartVariant {
    INGOT(0),
    NUGGET(1),
    ROD(2),
    GEAR(3),
    PLATE(4);

    private final int meta;
    SimplePartVariant(int meta) {
        this.meta = meta;
    }

    public int getMeta() {
        return meta;
    }

    @Override
    public String toString() {
        switch (this){
            default:
                return "ingot";
            case NUGGET:
                return "nugget";
            case ROD:
                return "rod";
            case GEAR:
                return "gear";
            case PLATE:
                return "plate";
        }
    }
}
