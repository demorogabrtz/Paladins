package net.paladins.client.armor;

import mod.azure.azurelibarmor.common.api.client.renderer.GeoArmorRenderer;
import net.paladins.item.armor.PriestArmor;

public class PriestArmorRenderer extends GeoArmorRenderer<PriestArmor> {
    public PriestArmorRenderer() {
        super(new PriestArmorModel());
    }
}
