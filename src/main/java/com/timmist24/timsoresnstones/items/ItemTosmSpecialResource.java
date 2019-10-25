package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;

public class ItemTosmSpecialResource extends ItemTosm {
    private final String resourceLocation;
    public ItemTosmSpecialResource(String itemName, String resourceLocation) {
        super(itemName);
        this.resourceLocation = resourceLocation;
    }
    @Override
    public void registerModels()
    {
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "inventory", "item."+ resourceLocation);
    }
}
