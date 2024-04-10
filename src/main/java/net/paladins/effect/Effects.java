package net.paladins.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.paladins.PaladinsMod;
import net.spell_engine.api.effect.ActionImpairing;
import net.spell_engine.api.effect.EntityActionsAllowed;
import net.spell_engine.api.effect.Synchronized;
import net.spell_power.api.SpellPowerMechanics;

public class Effects {
    public static StatusEffect DIVINE_PROTECTION = new DivineProtectionStatusEffect(StatusEffectCategory.BENEFICIAL, 0x66ccff);
    public static StatusEffect BATTLE_BANNER = new CustomStatusEffect(StatusEffectCategory.BENEFICIAL, 0x66ccff);
    public static StatusEffect JUDGEMENT = new JudgementStatusEffect(StatusEffectCategory.HARMFUL, 0xffffcc);
    public static StatusEffect ABSORPTION = new PriestAbsorptionStatusEffect(StatusEffectCategory.BENEFICIAL, 0xffffcc);

    public static String BATTLE_BANNER_BOOST_UUID = "052f3166-8a43-11ed-a1eb-0242ac120002";

    public static void register() {
        BATTLE_BANNER
                .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,
                        BATTLE_BANNER_BOOST_UUID,
                        PaladinsMod.effectsConfig.value.battle_banner_attack_speed_bonus,
                        EntityAttributeModifier.Operation.MULTIPLY_BASE)
                .addAttributeModifier(SpellPowerMechanics.HASTE.attribute,
                        BATTLE_BANNER_BOOST_UUID,
                        PaladinsMod.effectsConfig.value.battle_banner_spell_haste_bonus,
                        EntityAttributeModifier.Operation.MULTIPLY_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                        BATTLE_BANNER_BOOST_UUID,
                        PaladinsMod.effectsConfig.value.battle_banner_knockback_resistance_bonus,
                        EntityAttributeModifier.Operation.MULTIPLY_BASE)
        ;
        var rangedHasteAttribute = Registries.ATTRIBUTE.get(new Identifier("ranged_weapon", "haste"));
        if (rangedHasteAttribute != null) {
            BATTLE_BANNER.addAttributeModifier(rangedHasteAttribute,
                    BATTLE_BANNER_BOOST_UUID,
                    PaladinsMod.effectsConfig.value.battle_banner_ranged_haste_bonus,
                    EntityAttributeModifier.Operation.MULTIPLY_BASE);
        }

        Synchronized.configure(DIVINE_PROTECTION, true);
        Synchronized.configure(JUDGEMENT, true);
        Synchronized.configure(ABSORPTION, true);
        ActionImpairing.configure(JUDGEMENT, EntityActionsAllowed.STUN);

        int rawId = 710;
        Registry.register(Registries.STATUS_EFFECT, rawId++, new Identifier(PaladinsMod.ID, "divine_protection").toString(), DIVINE_PROTECTION);
        Registry.register(Registries.STATUS_EFFECT, rawId++, new Identifier(PaladinsMod.ID, "battle_banner").toString(), BATTLE_BANNER);
        Registry.register(Registries.STATUS_EFFECT, rawId++, new Identifier(PaladinsMod.ID, "judgement").toString(), JUDGEMENT);
        Registry.register(Registries.STATUS_EFFECT, rawId++, new Identifier(PaladinsMod.ID, "priest_absorption").toString(), ABSORPTION);
    }
}
