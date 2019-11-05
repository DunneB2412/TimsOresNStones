package com.timmist24.timsoresnstones.items.materials.ore;

import com.timmist24.timsoresnstones.items.materials.PureDustVariants;


public enum MineralVariant {
    METAL(PureDustVariants.values(), OrePieceVariants.values()),
    GEM(PureDustVariants.values(), OrePieceVariants.values()),
    CRYSTAL(PureDustVariants.values(), OrePieceVariants.values()),
    LIQUID(new PureDustVariants[]{}, new OrePieceVariants[]{OrePieceVariants.PIECE,OrePieceVariants.CHUNK});

    MineralVariant(PureDustVariants[] pureDustVariants, OrePieceVariants[] orePieceVariants) {
    }
}
