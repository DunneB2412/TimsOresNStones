package com.timmist24.timsoresnstones.items.materials;

public enum DustVariant {
    DUST(0, 144),
    DUST_SMALL(1, 16),
    DUST_TINY(2, 2);

    private final int meta;
    private final int mbResult;

    DustVariant(int meta, int mbResult) {
        this.meta = meta;
        this.mbResult = mbResult;
    }

    public int getMbResult() {
        return mbResult;
    }

    public int getMeta() {
        return meta;
    }

    @Override
    public String toString() {
        switch (this){
            case DUST:
                return "dust";
            case DUST_SMALL:
                return "dust_small";
            case DUST_TINY:
                return "dust_tiny";
        }
        return "";
    }
}
