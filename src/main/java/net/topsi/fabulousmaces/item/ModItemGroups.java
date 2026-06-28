package net.topsi.fabulousmaces.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.topsi.fabulousmaces.FabulousMaces;
import net.topsi.fabulousmaces.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FabulousMaces.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))
                    .displayName(Text.translatable("itemgroup.tutorialmod.pink_garnet_blocks"))
                    .entries((displayContext, entries) -> {
                        // entries.add(ModBlocks.PINK_GARNET_BLOCK);

                    }).build());


    public static void registerItemGroups() {
        FabulousMaces.LOGGER.info("Registering Item Groups for " + FabulousMaces.MOD_ID);
    }

}
