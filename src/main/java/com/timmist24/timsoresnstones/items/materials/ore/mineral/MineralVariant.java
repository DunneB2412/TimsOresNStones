package com.timmist24.timsoresnstones.items.materials.ore.mineral;

import com.timmist24.timsoresnstones.items.materials.DustVariant;
import com.timmist24.timsoresnstones.items.materials.PartVariant;
import com.timmist24.timsoresnstones.items.materials.ore.OreVariant;


public enum MineralVariant {
    METAL(DustVariant.values(), OreVariant.values(), PartVariant.values()),
    CERAMIC(DustVariant.values(), OreVariant.values(),new PartVariant[]{}),
    ORGANIC(DustVariant.values(), OreVariant.values(), new PartVariant[]{}),
    CHEMICAL(DustVariant.values(), OreVariant.values(), new PartVariant[]{}),
    POLYMER(new DustVariant[]{}, new OreVariant[]{OreVariant.PIECE, OreVariant.CHUNK}, new PartVariant[]{}),
    COMPOSITE(DustVariant.values(), new OreVariant[]{},PartVariant.values());

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
        return "Working progress:"+this.name();
//        switch (this){
//            case METAL:
//                return "ingot";
//            case CERAMIC:
//                return "gem";
//            case ORGANIC:
//                return "crystal";
//            case POLYMER:
//                return "liquid";
//            case COMPOSITE:
//                return "alloy";
//            default:
//                return super.toString();
//        }
    }
    public static MineralVariant getFromString(String key){
        return MineralVariant.valueOf(key.toUpperCase());
    }
}
