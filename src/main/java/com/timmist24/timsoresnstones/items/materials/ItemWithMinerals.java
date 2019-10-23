package com.timmist24.timsoresnstones.items.materials;

import com.timmist24.timsoresnstones.items.ItemBase;
import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemWithMinerals extends ItemBase {
    private final Mineral[] composition;
    private Color compositionTell;

    public ItemWithMinerals(String itemName, Mineral[] composition) {
        super(itemName);
        this.composition = composition;
    }

    public Item[] flotation(float eficancy){
        List<Mineral> oilDesolved = new ArrayList<>();
        List<Mineral> waterDesolved = new ArrayList<>();
        for(Mineral mineral: composition){
            Mineral extracted = mineral.extractMaterial(eficancy);
            if(extracted.isOilSoluble()){
                oilDesolved.add(extracted);
            }
            else {
                waterDesolved.add(extracted);
            }
        }
        OreOil oreOil = new OreOil("ore_oil"+oilDesolved, (Mineral[])oilDesolved.toArray());
        OreSlury oreSlury = new OreSlury("slury"+waterDesolved, (Mineral[])waterDesolved.toArray());
        return new Item[]{oreOil, oreSlury};
    }
    public Item[] washing(float eficancy, float strength, int splits){
        return new Item[]{};
    }
    public Item[] centrifugation(float maxRpm){
        return new Item[]{};
    }
    public Item[] sifting(float siveSise){
        return new Item[]{};
    }
    public Item[] magneticSeperation(){
        return new Item[]{};
    }
    public Item[] magic(float magicBonous){
        Item[] out = new Item[composition.length];
        for (int index = 0; index<composition.length; index++){
            Mineral mineral = composition[index];
            out[index] = new OreSlury(mineral.title+"_ore_slury", new Mineral[]{mineral.extractMaterial(100+magicBonous)});
        }
        return out;
    }

    private void updateValues(){
        float totalMineral = 0.0f;
        for (Mineral mineral: composition){
            float mineralAmount = mineral.getNetWeight();
            if(mineralAmount>0) {
            }
        }
    }
    private void updateColor(){
        Color newColor = new Color("00000000");
        for (Mineral mineral)
    }
}
