package net.paladins.config;

import net.spell_engine.api.item.ItemConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShieldsConfig {
    public static class Entry {
        public int durability = 150;
        public
        List<ItemConfig.Attribute> attributes = List.of();
    }

    public Map<String, Entry> shields = new HashMap<>();
}