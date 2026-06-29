package net.topsi.fabulousmaces.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.topsi.fabulousmaces.FabulousMaces;

import static net.topsi.fabulousmaces.item.ModItems.*;

public class ModItemGroups {

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FabulousMaces.MOD_ID, "fabulous_maces"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.FABULOUS_MACE))
                    .displayName(Text.translatable("itemgroup.fabulousmaces.fabulous_maces"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.IRON_MACE);
                        entries.add(ModItems.EMERALD_MACE);
                        entries.add(ModItems.DIAMOND_MACE);
                        entries.add(ModItems.NETHERITE_MACE);
                        entries.add(FIRE_MACE);
                        entries.add(ModItems.IRON_FIRE_MACE);
                        entries.add(ModItems.EMERALD_FIRE_MACE);
                        entries.add(ModItems.DIAMOND_FIRE_MACE);
                        entries.add(ModItems.NETHERITE_FIRE_MACE);
                        entries.add(ModItems.FABULOUS_MACE);

                        entries.add(IRON_ROD);
                        entries.add(DIAMOND_ROD);
                        entries.add(EMERALD_ROD);
                        entries.add(NETHERITE_ROD);

                    }).build());


    public static void registerItemGroups() {
        FabulousMaces.LOGGER.info("Registering Item Groups for " + FabulousMaces.MOD_ID);
    }

}
