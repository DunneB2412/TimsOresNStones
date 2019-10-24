package com.timmist24.timsoresnstones.items;

import com.timmist24.timsoresnstones.TimsOresNStonesMain;

public class ItemTonsSpecialResorce extends ItemTons {
    private final String resorceLocation;
    public ItemTonsSpecialResorce(String itemName, String resorceLocation) {
        super(itemName);
        this.resorceLocation = resorceLocation;
    }
    @Override
    public void registerModels()
    {
        TimsOresNStonesMain.proxy.registorItemRenderer(this, 0, "inventory", resorceLocation);
    }
}
