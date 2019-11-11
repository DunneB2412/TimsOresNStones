package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.items.materials.DustVariant;
import com.timmist24.timsoresnstones.items.materials.ore.OreVariant;


public enum MineralVariant {
    METAL(DustVariant.values(), OreVariant.values()),
    GEM(DustVariant.values(), OreVariant.values()),
    CRYSTAL(DustVariant.values(), OreVariant.values()),
    LIQUID(new DustVariant[]{}, new OreVariant[]{OreVariant.PIECE, OreVariant.CHUNK});

    private final DustVariant[] dustVariants;
    private final OreVariant[] oreVariants;
    MineralVariant(DustVariant[] dustVariants, OreVariant[] oreVariants) {
        this.dustVariants=dustVariants;
        this.oreVariants=oreVariants;
    }

    public DustVariant[] getDustVariants() {
        return dustVariants;
    }

    public OreVariant[] getOreVariants() {
        return oreVariants;
    }

    @Override
    public String toString() {
        switch (this){
            case METAL:
                return "ingot";
            case GEM:
                return "gem";
            case CRYSTAL:
                return "crystal";
            case LIQUID:
                return "liquid";
            default:
                return super.toString();
        }
    }
    public static MineralVariant getFromString(String key){
        switch (key){
            case "ingot":
                return MineralVariant.METAL;
            case "gem":
                return MineralVariant.GEM;
            case "liquid":
                return MineralVariant.LIQUID;
            default:
                return MineralVariant.CRYSTAL;
        }
    }
}
