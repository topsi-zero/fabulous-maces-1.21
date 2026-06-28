package net.topsi.fabulousmaces.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.topsi.fabulousmaces.FabulousMaces;


public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FabulousMaces.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FabulousMaces.LOGGER.info("Registering Mod Items for " + FabulousMaces.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            // entries.add(...);
        });
    }

}
