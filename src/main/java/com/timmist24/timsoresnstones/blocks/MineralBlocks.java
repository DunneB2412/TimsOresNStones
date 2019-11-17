package com.timmist24.timsoresnstones.blocks;

public enum MineralBlocks {
    SOLID_BOCK(0, 1296, 1.0f),
    I_BEAM(1, 576, 0.5f),
    PLATED_I_BEAM(2, 1152, 0.75);

    private final int meta;
    private final int milleBuckets;
    private final double strengthMultilier;
    MineralBlocks(int meta, int milleBuckets, double strengthMultiplier) {
        this.meta = meta;
        this.milleBuckets = milleBuckets;
        this.strengthMultilier = strengthMultiplier;
    }

    public int getMeta() {
        return meta;
    }

    public int getMilleBuckets() {
        return milleBuckets;
    }

    public double getStrengthMultilier() {
        return strengthMultilier;
    }

    @Override
    public String toString() {
        switch (this){
            default:
                return "block";
            case I_BEAM:
                return "i_beam";
            case PLATED_I_BEAM:
                return "plate_block";
        }
    }
}
