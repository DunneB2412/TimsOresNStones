package com.timmist24.timsoresnstones.items.materials.ore;

public enum OrePieceVariants {
    PIECE(0),
    CHUNK(1),
    DUST(2),
    OILY_BALL(3),
    WET_BALL(4);

    private final int meta;
    OrePieceVariants(int meta) {
        this.meta = meta;
    }

    public final int getMeta(){
        return this.meta;
    }

    @Override
    public String toString() {
        switch (this){
            case PIECE:
                return "ore_piece";
            case CHUNK:
                return "ore_chunk";
            case DUST:
                return "ore_dust";
            case OILY_BALL:
                return "ore_oily_ball";
            case WET_BALL:
                return "ore_wet_ball";
        }
        return "";
    }
}
