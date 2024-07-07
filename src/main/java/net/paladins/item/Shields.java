package net.paladins.item;

import com.github.theredbrain.shieldapi.item.CustomShieldItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.paladins.PaladinsMod;
import net.spell_engine.api.item.weapon.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shields {
    public record Entry(CustomShieldItem item, Identifier id) {  }
    public static final ArrayList<Entry> ENTRIES = new ArrayList<>();

    public static Entry shield(Identifier id) {
        var entry = new Entry(new CustomShieldItem(
                null,
                () -> Ingredient.ofItems(Items.IRON_INGOT),
                List.of(
                        new Pair<>(
                                EntityAttributes.GENERIC_ARMOR,
                                new EntityAttributeModifier(
                                        "test_shield_armor",
                                        4.0,
                                        EntityAttributeModifier.Operation.ADDITION
                                )
                        )
                ),
                new FabricItemSettings().maxDamage(150)), id);
        ENTRIES.add(entry);
        return entry;
    }

    public static Entry iron_kite_shield = shield(new Identifier(PaladinsMod.ID, "iron_kite_shield"));
    public static Entry golden_kite_shield = shield(new Identifier(PaladinsMod.ID, "golden_kite_shield"));
    public static Entry diamond_kite_shield = shield(new Identifier(PaladinsMod.ID, "diamond_kite_shield"));
    public static Entry netherite_kite_shield = shield(new Identifier(PaladinsMod.ID, "netherite_kite_shield"));
    public static Entry aeternium_kite_shield = shield(new Identifier(PaladinsMod.ID, "aeternium_kite_shield"));
    public static Entry ruby_kite_shield = shield(new Identifier(PaladinsMod.ID, "ruby_kite_shield"));

    public static void register() {
        for (var entry: ENTRIES) {
            Registry.register(Registries.ITEM, entry.id, entry.item);
        }

        ItemGroupEvents.modifyEntriesEvent(Group.KEY).register((content) -> {
            for (var entry: ENTRIES) {
                content.add(entry.item);
            }
        });
    }
}
