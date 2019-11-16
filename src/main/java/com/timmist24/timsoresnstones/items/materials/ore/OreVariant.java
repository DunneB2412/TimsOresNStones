package com.timmist24.timsoresnstones.items.materials.ore;

public enum OreVariant {
    PIECE(0),
    CHUNK(1),
    GRANE(2),
    DUST(3),
    OILY_BALL(4),
    WET_BALL(5),
    SCRAP(6);

    private final int meta;
    OreVariant(int meta) {
        this.meta = meta;
    }

    public final int getMeta(){
        return this.meta;
    }

    @Override
    public String toString() {
        switch (this){
            default:
                return "ore_piece";
            case CHUNK:
                return "ore_chunk";
            case GRANE:
                return "ore_grane";
            case DUST:
                return "ore_dust";
            case OILY_BALL:
                return "ore_oily_ball";
            case WET_BALL:
                return "ore_wet_ball";
            case SCRAP:
                return "scrap";
        }
    }
}
