package com.timmist24.timsoresnstones.items.materials;

import com.timmist24.timsoresnstones.texturing.Color;

public class MineralUnstable extends Mineral {
    public MineralUnstable(String title, boolean isOilSoluble, float weightPerUnit, Color color) {
        super(title, isOilSoluble, weightPerUnit, color);
    }
    public MineralUnstable(String title, boolean isOilSoluble, float weightPerUnit, Color color, int quantity) {
        super(title, isOilSoluble, weightPerUnit, color, quantity);
    }
    // overide access to be unstable.
}
