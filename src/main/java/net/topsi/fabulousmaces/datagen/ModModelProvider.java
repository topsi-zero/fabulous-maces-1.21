package net.topsi.fabulousmaces.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.topsi.fabulousmaces.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(ModItems.IRON_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_FIRE_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EMERALD_FIRE_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_FIRE_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_FIRE_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FABULOUS_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FIRE_MACE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.IRON_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMERALD_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHERITE_ROD, Models.GENERATED);
    }
}
