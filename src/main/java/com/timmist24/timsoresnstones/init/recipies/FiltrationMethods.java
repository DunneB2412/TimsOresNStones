package com.timmist24.timsoresnstones.init.recipies;

import net.minecraft.item.Item;


public  enum FiltrationMethods {
    CRUSHING(((itemIn, strength) -> new Item[0])),
    MAGNETIC((itemIn, strength) -> new Item[0]),
    SIFTING((itemIn, strength) -> new Item[0]),
    WASHING((itemIn, strength) -> new Item[0]),
    FLOTATION((itemIn, strength) -> new Item[0]),
    CENTERFUGIC((itemIn, strength) -> new Item[0]),
    MAGIC((itemIn, strength) -> new Item[0]);

    private final Filtration function;
    FiltrationMethods(Filtration filtration) {
        this.function= filtration;
    }
    public Filtration getFunction(){
        return function;
    }
}
