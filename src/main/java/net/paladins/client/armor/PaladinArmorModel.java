package net.paladins.client.armor;

import mod.azure.azurelibarmor.common.api.client.model.GeoModel;
import net.minecraft.util.Identifier;
import net.paladins.PaladinsMod;
import net.paladins.item.armor.PaladinArmor;

public class PaladinArmorModel extends GeoModel<PaladinArmor> {
    @Override
    public Identifier getModelResource(PaladinArmor object) {
        return Identifier.of(PaladinsMod.ID, "geo/paladin_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(PaladinArmor armor) {
        var textureId = armor.getFirstLayerId();
        return Identifier.of(textureId.getNamespace(), "textures/armor/" + textureId.getPath() + ".png");
    }

    @Override
    public Identifier getAnimationResource(PaladinArmor animatable) {
        return null; // Identifier.of(PaladinsMod.ID, "animations/armor_idle.json");
    }
}
