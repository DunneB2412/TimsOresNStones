package com.timmist24.timsoresnstones.items.materials;

public enum PartVariant {
    INGOT(0,144),
    NUGGET(1, 16),
    ROD(2, 144),
    HEAVY_ROD(3, 288),
    GEAR(4, 144),
    PLATE(5, 144),
    LARG_PLATE(6, 288);

    private final int meta;
    private final int milleBuckets;
    PartVariant(int meta, int milleBuckets) {
        this.meta = meta;
        this.milleBuckets = milleBuckets;
    }
    public int getMeta() {
        return meta;
    }

    public int getMilleBuckets() {
        return milleBuckets;
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
            case HEAVY_ROD:
                return "heavy_rod";
            case GEAR:
                return "gear";
            case PLATE:
                return "plate";
            case LARG_PLATE:
                return "large_plate";
        }
    }
}
