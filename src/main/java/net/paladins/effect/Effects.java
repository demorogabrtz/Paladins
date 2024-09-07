package net.paladins.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.paladins.PaladinsMod;
import net.spell_engine.api.effect.ActionImpairing;
import net.spell_engine.api.effect.EntityActionsAllowed;
import net.spell_engine.api.effect.Synchronized;
import net.spell_power.api.SpellPowerMechanics;

import java.util.ArrayList;

public class Effects {
    private static ArrayList<Entry> entries = new ArrayList<>();
    public static class Entry {
        public final Identifier id;
        public final StatusEffect effect;
        public RegistryEntry<StatusEffect> registryEntry;

        public Entry(String name, StatusEffect effect) {
            this.id = Identifier.of(PaladinsMod.ID, name);
            this.effect = effect;
            entries.add(this);
        }

        public void register() {
            registryEntry = Registry.registerReference(Registries.STATUS_EFFECT, id, effect);
        }

        public Identifier modifierId() {
            return Identifier.of(PaladinsMod.ID, "effect." + id.getPath());
        }
    }

    public static final Entry DIVINE_PROTECTION = new Entry("divine_protection",
            new DivineProtectionStatusEffect(StatusEffectCategory.BENEFICIAL, 0x66ccff));
    public static final Entry BATTLE_BANNER = new Entry("battle_banner",
            new CustomStatusEffect(StatusEffectCategory.BENEFICIAL, 0x66ccff));
    public static final Entry JUDGEMENT = new Entry("judgement",
            new JudgementStatusEffect(StatusEffectCategory.HARMFUL, 0xffffcc));
    public static final Entry ABSORPTION = new Entry("priest_absorption",
            new PriestAbsorptionStatusEffect(StatusEffectCategory.BENEFICIAL, 0xffffcc));

    public static void register() {
        BATTLE_BANNER.effect
                .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,
                        BATTLE_BANNER.modifierId(),
                        PaladinsMod.tweaksConfig.value.battle_banner_attack_speed_bonus,
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                .addAttributeModifier(SpellPowerMechanics.HASTE.attributeEntry,
                        BATTLE_BANNER.modifierId(),
                        PaladinsMod.tweaksConfig.value.battle_banner_spell_haste_bonus,
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                        BATTLE_BANNER.modifierId(),
                        PaladinsMod.tweaksConfig.value.battle_banner_knockback_resistance_bonus,
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        ;
        var rangedHasteAttribute = Registries.ATTRIBUTE.getEntry(Identifier.of("ranged_weapon", "haste"));
        if (rangedHasteAttribute.isPresent()) {
            BATTLE_BANNER.effect.addAttributeModifier(rangedHasteAttribute.get(),
                    BATTLE_BANNER.modifierId(),
                    PaladinsMod.tweaksConfig.value.battle_banner_ranged_haste_bonus,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        }

        Synchronized.configure(DIVINE_PROTECTION.effect, true);
        Synchronized.configure(JUDGEMENT.effect, true);
        Synchronized.configure(ABSORPTION.effect, true);
        ActionImpairing.configure(JUDGEMENT.effect, EntityActionsAllowed.STUN);

        for (var entry: entries) {
            entry.register();
        }
    }
}
