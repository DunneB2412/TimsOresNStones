package com.timmist24.timsoresnstones.items.materials.ore;

public enum OrePieceStates {
    PIECE(0),
    CHUNK(1),
    DUST(2),
    OILY_BALL(3),
    WET_BALL(4);

    private final int meta;
    OrePieceStates(int meta) {
        this.meta = meta;
    }

    public static int size() {
        return 5;
    }

    public final int getMeta(){
        return this.meta;
    }

    @Override
    public String toString() {
        switch (this){
            case PIECE:
                return "piece";
            case CHUNK:
                return "chunk";
            case DUST:
                return "dust";
            case OILY_BALL:
                return "oily_ball";
            case WET_BALL:
                return "wet_ball";
        }
        return "";
    }
}
