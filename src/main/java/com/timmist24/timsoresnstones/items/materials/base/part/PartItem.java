package com.timmist24.timsoresnstones.items.materials.base.part;


import com.timmist24.timsoresnstones.items.materials.base.MinerlaVariedItem;

public class PartItem extends MinerlaVariedItem {
    public PartItem(String itemName) {
        super(itemName);
    }
    @Override
    protected void prepareItemstacks() {
        for(PartVariant partVariant: PartVariant.values()){
            super.prepareItemstacks(partVariant.ordinal());
        }
    }
    @Override
    public String variantTitle(int i){
        return PartVariant.values()[i].toString();
    }

}
