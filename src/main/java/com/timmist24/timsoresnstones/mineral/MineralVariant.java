package com.timmist24.timsoresnstones.mineral;


public class MineralVariant {
    private static final char[] MINERAL_TYPE_MASKS = {
            0   ,//metal        {pure,          steals,         other}
            1<<7,//ceramic      {engineering,   traditional,    stone}
            2<<7,//polymer      {thermostatic,  thermoplastic,  elastomers}
            3<<7,//composite    {synthetic,     organic,        magical} (magic has to be woven into the material)
            1<<5,//sub var 1
            2<<5,//sub var 2
            3<<5,//sub var 3
    };
    public static String typeAsString(char type){
        StringBuilder stringBuilder = new StringBuilder();
        switch ((type&3<<7)>>7){
            case 0:
                stringBuilder.append("Metal:");
                switch ((type&3<<5)>>5){
                    case 1:
                        stringBuilder.append("Pure:");
                        break;
                    case 2:
                        stringBuilder.append("Steel:");
                        break;
                    default:
                        stringBuilder.append("Alloy:");
                }
                break;
            case 1:
                stringBuilder.append("Ceramic:");
                switch ((type&3<<5)>>5){
                    case 1:
                        stringBuilder.append("Engineering:");
                        break;
                    case 2:
                        stringBuilder.append("Traditional:");
                        break;
                    default:
                        stringBuilder.append("Stone:");
                }
                break;
            case 2:
                stringBuilder.append("Polymer:");
                switch ((type&3<<5)>>5){
                    case 1:
                        stringBuilder.append("Thermostatic:");
                        break;
                    case 2:
                        stringBuilder.append("Thermoplastic:");
                        break;
                    default:
                        stringBuilder.append("Elastomers:");
                }
                break;
            default:
                stringBuilder.append("Composite:");
                switch ((type&3<<5)>>5){
                    case 1:
                        stringBuilder.append("Engineering:");
                        break;
                    case 2:
                        stringBuilder.append("Organic:");
                        break;
                    default:
                        stringBuilder.append("Magical:");
                }
        }
        return stringBuilder.toString();
    }



//    METAL(DustVariant.values(), OreVariant.values(), PartVariant.values()),
//    CERAMIC(DustVariant.values(), OreVariant.values(),new PartVariant[]{}),
//    ORGANIC(DustVariant.values(), OreVariant.values(), new PartVariant[]{}),
//    CHEMICAL(DustVariant.values(), OreVariant.values(), new PartVariant[]{}),
//    POLYMER(new DustVariant[]{}, new OreVariant[]{OreVariant.PIECE, OreVariant.CHUNK}, new PartVariant[]{}),
//    COMPOSITE(DustVariant.values(), new OreVariant[]{},PartVariant.values());

//    private final DustVariant[] dustVariants;
//    private final OreVariant[] oreVariants;
//    private final PartVariant[] partVariants;
//    MineralVariant(DustVariant[] dustVariants, OreVariant[] oreVariants, PartVariant[] partVariants) {
//        this.dustVariants=dustVariants;
//        this.oreVariants=oreVariants;
//        this.partVariants = partVariants;
//    }
//
//    public Enum[] getDustVariants(Enum targetEnum) {
//        if(targetEnum.getClass()==DustVariant.class){
//            return this.dustVariants;
//        }
//        if(targetEnum.getClass()==OreVariant.class){
//            return this.oreVariants;
//        }
//        return this.partVariants;
//    }
//
//    @Override
//    public String toString() {
//        return "Working progress:"+this.name();
////        switch (this){
////            case METAL:
////                return "ingot";
////            case CERAMIC:
////                return "gem";
////            case ORGANIC:
////                return "organic";
////            case POLYMER:
////                return "liquid";
////            case COMPOSITE:
////                return "alloy";
////            default:
////                return super.toString();
////        }
//    }
//    public static MineralVariant getFromString(String key){
//        return MineralVariant.valueOf(key.toUpperCase());
//    }
}
