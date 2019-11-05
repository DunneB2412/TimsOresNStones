package com.timmist24.timsoresnstones.init.recipies;

import com.timmist24.timsoresnstones.items.materials.ore.mineral.Mineral;
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
        //OrePiece oreOil = new OrePiece("ore_oil"+oilDesolved, (Mineral[])oilDesolved.toArray());
        //OrePiece oreSlury = new OrePiece("slury"+waterDesolved, (Mineral[])waterDesolved.toArray());
        return new Item[0]; //{itemIn, oreOil, oreSlury};
    }),
    CENTERFUGIC((itemIn, strength) -> new Item[0]),
    MAGIC((itemIn, strength) -> {
        Item[] out = new Item[itemIn.composition.size()];
        for (int index = 0; index<itemIn.composition.size(); index++){
            Mineral mineral = itemIn.composition.get(index);
            //out[index] = new OrePiece(mineral.title+"_ore_piece", new Mineral[]{mineral.extractMaterial(100+strength)});
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
