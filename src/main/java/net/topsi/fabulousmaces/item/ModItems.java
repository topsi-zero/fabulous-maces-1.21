package net.topsi.fabulousmaces.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.topsi.fabulousmaces.FabulousMaces;
import net.topsi.fabulousmaces.item.custom.*;


public class ModItems {

    public static final Item IRON_ROD = registerItem("iron_rod" ,new Item(new Item.Settings()));
    public static final Item EMERALD_ROD = registerItem("emerald_rod" ,new Item(new Item.Settings()));
    public static final Item DIAMOND_ROD = registerItem("diamond_rod" ,new Item(new Item.Settings()));
    public static final Item NETHERITE_ROD = registerItem("netherite_rod" ,new Item(new Item.Settings()));


    public static final Item IRON_MACE = registerItem(
            "iron_mace",
            new IronMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, IronMaceItem.createToolComponent())
                            .attributeModifiers(IronMaceItem.createAttributeModifiers())
            )
    );

    public static final Item EMERALD_MACE = registerItem(
            "emerald_mace",
            new EmeraldMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, EmeraldMaceItem.createToolComponent())
                            .attributeModifiers(EmeraldMaceItem.createAttributeModifiers())
            )
    );

    public static final Item DIAMOND_MACE = registerItem(
            "diamond_mace",
            new DiamondMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, DiamondMaceItem.createToolComponent())
                            .attributeModifiers(DiamondMaceItem.createAttributeModifiers())
            )
    );

    public static final Item NETHERITE_MACE = registerItem(
            "netherite_mace",
            new NetheriteMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, NetheriteMaceItem.createToolComponent())
                            .attributeModifiers(NetheriteMaceItem.createAttributeModifiers())
            )
    );

    public static final Item FIRE_MACE = registerItem(
            "fire_mace",
            new FireMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, FireMaceItem.createToolComponent())
                            .attributeModifiers(FireMaceItem.createAttributeModifiers())
            )
    );

    public static final Item IRON_FIRE_MACE = registerItem(
            "iron_fire_mace",
            new IronFireMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, IronFireMaceItem.createToolComponent())
                            .attributeModifiers(IronFireMaceItem.createAttributeModifiers())
            )
    );

    public static final Item EMERALD_FIRE_MACE = registerItem(
            "emerald_fire_mace",
            new EmeraldFireMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, EmeraldFireMaceItem.createToolComponent())
                            .attributeModifiers(EmeraldFireMaceItem.createAttributeModifiers())
            )
    );

    public static final Item DIAMOND_FIRE_MACE = registerItem(
            "diamond_fire_mace",
            new DiamondFireMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, DiamondFireMaceItem.createToolComponent())
                            .attributeModifiers(DiamondFireMaceItem.createAttributeModifiers())
            )
    );

    public static final Item NETHERITE_FIRE_MACE = registerItem(
            "netherite_fire_mace",
            new NetheriteFireMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, NetheriteFireMaceItem.createToolComponent())
                            .attributeModifiers(NetheriteFireMaceItem.createAttributeModifiers())
            )
    );

    public static final Item FABULOUS_MACE = registerItem(
            "fabulous_mace",
            new FabulousMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, FabulousMaceItem.createToolComponent())
                            .attributeModifiers(FabulousMaceItem.createAttributeModifiers())
            )
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FabulousMaces.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FabulousMaces.LOGGER.info("Registering Mod Items for " + FabulousMaces.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(IRON_MACE);
            entries.add(IRON_FIRE_MACE);
            entries.add(EMERALD_MACE);
            entries.add(EMERALD_FIRE_MACE);
            entries.add(DIAMOND_MACE);
            entries.add(DIAMOND_FIRE_MACE);
            entries.add(NETHERITE_MACE);
            entries.add(NETHERITE_FIRE_MACE);
            entries.add(FABULOUS_MACE);
            entries.add(FIRE_MACE);

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(IRON_ROD);
            entries.add(DIAMOND_ROD);
            entries.add(EMERALD_ROD);
            entries.add(NETHERITE_ROD);

        });
    }

}
