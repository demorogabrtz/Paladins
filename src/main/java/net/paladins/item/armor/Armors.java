package net.paladins.item.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.paladins.PaladinsMod;
import net.paladins.item.Group;
import net.paladins.util.SoundHelper;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.armor.Armor;
import net.spell_power.api.SpellPowerMechanics;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Armors {
    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();
    private static Armor.Entry create(RegistryEntry<ArmorMaterial> material, Identifier id, int durability,
                                      Armor.Set.ItemFactory factory, ItemConfig.ArmorSet defaults) {
        var entry = Armor.Entry.create(
                material,
                id,
                durability,
                factory,
                defaults);
        entries.add(entry);
        return entry;
    }

    public static RegistryEntry<ArmorMaterial> material(
            String name, int protectionHead, int protectionChest, int protectionLegs, int protectionFeet,
            int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredient) {

        var material = new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.HELMET, protectionHead,
                        ArmorItem.Type.CHESTPLATE, protectionChest,
                        ArmorItem.Type.LEGGINGS, protectionLegs,
                        ArmorItem.Type.BOOTS, protectionFeet),
                enchantability, equipSound, repairIngredient,
                List.of(new ArmorMaterial.Layer(Identifier.of(PaladinsMod.ID, name))),
                0,0
        );
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(PaladinsMod.ID, name), material);
    }

    public static RegistryEntry<ArmorMaterial> paladin_armor = material(
            "paladin_armor",
            2, 6, 5, 2,
            9,
            SoundHelper.paladin_armor_equip.entry(), () -> { return Ingredient.ofItems(Items.IRON_INGOT); });

    public static RegistryEntry<ArmorMaterial> crusader_armor = material(
            "crusader_armor",
            3, 8, 6, 3,
            10,
            SoundHelper.paladin_armor_equip.entry(), () -> { return Ingredient.ofItems(Items.GOLD_INGOT); });

    public static RegistryEntry<ArmorMaterial> netherite_crusader_armor = material(
            "netherite_crusader_armor",
            3, 8, 6, 3,
            15,
            SoundHelper.paladin_armor_equip.entry(), () -> { return Ingredient.ofItems(Items.NETHERITE_INGOT); });

    public static RegistryEntry<ArmorMaterial> priest_robe = material(
            "priest_robe",
            1, 3, 2, 1,
            9,
            SoundHelper.priest_robe_equip.entry(), () -> { return Ingredient.fromTag(ItemTags.WOOL); });

    public static RegistryEntry<ArmorMaterial> prior_robe = material(
            "prior_robe",
            1, 3, 2, 1,
            10,
            SoundHelper.priest_robe_equip.entry(), () -> { return Ingredient.ofItems(Items.GOLD_INGOT); });

    public static RegistryEntry<ArmorMaterial> netherite_prior_robe = material(
            "netherite_prior_robe",
            1, 3, 2, 1,
            15,
            SoundHelper.priest_robe_equip.entry(), () -> { return Ingredient.ofItems(Items.NETHERITE_INGOT); });

    public static final Armor.Set paladinArmorSet_t1 = create(
            paladin_armor,
            Identifier.of(PaladinsMod.ID, "paladin_armor"),
            15,
            PaladinArmor::new,
            ItemConfig.ArmorSet.with(
                    new ItemConfig.ArmorSet.Piece(2)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 0.5F)),
                    new ItemConfig.ArmorSet.Piece(6)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 0.5F)),
                    new ItemConfig.ArmorSet.Piece(5)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 0.5F)),
                    new ItemConfig.ArmorSet.Piece(2)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 0.5F))
            ))
            .armorSet();

    public static final Armor.Set paladinArmorSet_t2 = create(
            crusader_armor,
            Identifier.of(PaladinsMod.ID, "crusader_armor"),
            25,
            PaladinArmor::new,
            ItemConfig.ArmorSet.with(
                    new ItemConfig.ArmorSet.Piece(3)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1)),
                    new ItemConfig.ArmorSet.Piece(8)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1)),
                    new ItemConfig.ArmorSet.Piece(6)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1)),
                    new ItemConfig.ArmorSet.Piece(3)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1))
            ))
            .armorSet();

    public static final Armor.Set paladinArmorSet_t3 = create(
            netherite_crusader_armor,
            Identifier.of(PaladinsMod.ID, "netherite_crusader_armor"),
            37,
            PaladinArmor::new,
            ItemConfig.ArmorSet.with(
                    new ItemConfig.ArmorSet.Piece(3)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1)),
                    new ItemConfig.ArmorSet.Piece(8)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1)),
                    new ItemConfig.ArmorSet.Piece(6)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1)),
                    new ItemConfig.ArmorSet.Piece(3)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), 1))
            ))
            .armorSet();

    private static final float priest_t1_spell_power = 0.2F;
    private static final float priest_t2_spell_power = 0.25F;
    private static final float priest_t2_haste = 0.05F;
    private static final float priest_t3_spell_power = 0.25F;
    private static final float priest_t3_haste = 0.05F;
    private static final float priest_t3_crit_chance = 0.03F;

    public static final Armor.Set priestArmorSet_t1 = create(
            priest_robe,
            Identifier.of(PaladinsMod.ID, "priest_robe"),
            10,
            PriestArmor::new,
            ItemConfig.ArmorSet.with(
                    new ItemConfig.ArmorSet.Piece(1)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), priest_t1_spell_power)),
                    new ItemConfig.ArmorSet.Piece(3)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), priest_t1_spell_power)),
                    new ItemConfig.ArmorSet.Piece(2)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), priest_t1_spell_power)),
                    new ItemConfig.ArmorSet.Piece(1)
                            .addAll(ItemConfig.Attribute.bonuses(List.of(SpellSchools.HEALING.id), priest_t1_spell_power))
            ))
            .armorSet();

    public static final Armor.Set priestArmorSet_t2 = create(
            prior_robe,
            Identifier.of(PaladinsMod.ID, "prior_robe"),
            20,
            PriestArmor::new,
            ItemConfig.ArmorSet.with(
                    new ItemConfig.ArmorSet.Piece(1)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t2_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t2_haste)
                            )),
                    new ItemConfig.ArmorSet.Piece(3)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t2_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t2_haste)
                            )),
                    new ItemConfig.ArmorSet.Piece(2)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t2_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t2_haste)
                            )),
                    new ItemConfig.ArmorSet.Piece(1)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t2_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t2_haste)
                            ))
            ))
            .armorSet();

    public static final Armor.Set priestArmorSet_t3 = create(
            netherite_prior_robe,
            Identifier.of(PaladinsMod.ID, "netherite_prior_robe"),
            30,
            PriestArmor::new,
            ItemConfig.ArmorSet.with(
                    new ItemConfig.ArmorSet.Piece(1)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t3_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t3_haste)
                            )),
                    new ItemConfig.ArmorSet.Piece(3)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t3_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t3_haste)
                            )),
                    new ItemConfig.ArmorSet.Piece(2)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t3_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t3_haste)
                            )),
                    new ItemConfig.ArmorSet.Piece(1)
                            .addAll(List.of(
                                    ItemConfig.Attribute.multiply(SpellSchools.HEALING.id, priest_t3_spell_power),
                                    ItemConfig.Attribute.multiply(SpellPowerMechanics.HASTE.id, priest_t3_haste)
                            ))
            ))
            .armorSet();

    public static void register(Map<String, ItemConfig.ArmorSet> configs) {
        Armor.register(configs, entries, Group.KEY);
    }
}

