package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.ore.Mineral;
import com.timmist24.timsoresnstones.items.materials.ore.OreOil;
import com.timmist24.timsoresnstones.items.materials.ore.OreSlury;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public  enum FiltrationMethods {
    CRUSHING(((itemIn, strength) -> new Item[0])),
    MAGNETIC((itemIn, strength) -> new Item[0]),
    SIFTING((itemIn, strength) -> new Item[0]),
    WASHING((itemIn, strength) -> new Item[0]),
    FLOTATION((itemIn, strength) -> {
        List<Mineral> oilDesolved = new ArrayList<>();
        List<Mineral> waterDesolved = new ArrayList<>();
        for(Mineral mineral: itemIn.composition){
            Mineral extracted = mineral.extractMaterial(strength);
            if(extracted.isOilSoluble()){
                oilDesolved.add(extracted);
            }
            else {
                waterDesolved.add(extracted);
            }
        }
        OreOil oreOil = new OreOil("ore_oil"+oilDesolved, (Mineral[])oilDesolved.toArray());
        OreSlury oreSlury = new OreSlury("slury"+waterDesolved, (Mineral[])waterDesolved.toArray());
        return new Item[]{itemIn, oreOil, oreSlury};
    }),
    CENTERFUGIC((itemIn, strength) -> new Item[0]),
    MAGIC((itemIn, strength) -> {
        Item[] out = new Item[itemIn.composition.length];
        for (int index = 0; index<itemIn.composition.length; index++){
            Mineral mineral = itemIn.composition[index];
            out[index] = new OreSlury(mineral.title+"_ore_slury", new Mineral[]{mineral.extractMaterial(100+strength)});
        }
        return out;
    });

    private final Filtration function;
    FiltrationMethods(Filtration filtration) {
        this.function= filtration;
    }
    public Filtration getFunction(){
        return function;
    }
}
