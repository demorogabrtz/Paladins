package net.paladins.client.armor;

import mod.azure.azurelibarmor.common.api.client.renderer.GeoArmorRenderer;
import net.paladins.item.armor.PaladinArmor;

public class PaladinArmorRenderer extends GeoArmorRenderer<PaladinArmor> {
    public PaladinArmorRenderer() {
        super(new PaladinArmorModel());
    }
}
