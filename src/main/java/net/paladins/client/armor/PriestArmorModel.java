package net.paladins.client.armor;

import mod.azure.azurelibarmor.common.api.client.model.GeoModel;
import net.minecraft.util.Identifier;
import net.paladins.PaladinsMod;
import net.paladins.item.armor.PriestArmor;
import net.spell_engine.api.item.armor.Armor;

public class PriestArmorModel extends GeoModel<PriestArmor> {
    @Override
    public Identifier getModelResource(PriestArmor object) {
        return Identifier.of(PaladinsMod.ID, "geo/priest_robes.geo.json");
    }

    @Override
    public Identifier getTextureResource(PriestArmor armor) {
        var textureId = armor.getFirstLayerId();
        return Identifier.of(textureId.getNamespace(), "textures/armor/" + textureId.getPath() + ".png");
    }

    @Override
    public Identifier getAnimationResource(PriestArmor animatable) {
        return null; // Identifier.of(PaladinsMod.ID, "animations/armor_idle.json");
    }
}
