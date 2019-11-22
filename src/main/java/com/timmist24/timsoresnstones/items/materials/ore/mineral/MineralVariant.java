package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.items.materials.DustVariant;
import com.timmist24.timsoresnstones.items.materials.PartVariant;
import com.timmist24.timsoresnstones.items.materials.ore.OreVariant;


public enum MineralVariant {
    METAL(DustVariant.values(), OreVariant.values(), PartVariant.values()),
    GEM(DustVariant.values(), OreVariant.values(),new PartVariant[]{}),
    CRYSTAL(DustVariant.values(), OreVariant.values(), new PartVariant[]{}),
    LIQUID(new DustVariant[]{}, new OreVariant[]{OreVariant.PIECE, OreVariant.CHUNK}, new PartVariant[]{}),
    SYNTETIC(DustVariant.values(), new OreVariant[]{},PartVariant.values());

    private final DustVariant[] dustVariants;
    private final OreVariant[] oreVariants;
    private final PartVariant[] partVariants;
    MineralVariant(DustVariant[] dustVariants, OreVariant[] oreVariants, PartVariant[] partVariants) {
        this.dustVariants=dustVariants;
        this.oreVariants=oreVariants;
        this.partVariants = partVariants;
    }

    public Enum[] getDustVariants(Enum targetEnum) {
        if(targetEnum.getClass()==DustVariant.class){
            return this.dustVariants;
        }
        if(targetEnum.getClass()==OreVariant.class){
            return this.oreVariants;
        }
        return this.partVariants;
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
            case SYNTETIC:
                return "alloy";
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
