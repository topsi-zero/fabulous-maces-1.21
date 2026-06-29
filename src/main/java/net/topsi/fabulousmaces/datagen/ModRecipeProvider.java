package net.topsi.fabulousmaces.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.topsi.fabulousmaces.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.IRON_ROD)
                .pattern("  i")
                .pattern(" i ")
                .pattern("i  ")
                .input('i', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(ModItems.IRON_ROD))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.EMERALD_ROD)
                .pattern("  e")
                .pattern(" e ")
                .pattern("e  ")
                .input('e', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(ModItems.EMERALD_ROD))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DIAMOND_ROD)
                .pattern("  d")
                .pattern(" d ")
                .pattern("d  ")
                .input('d', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(ModItems.DIAMOND_ROD))
                .offerTo(recipeExporter);

        offerNetheriteUpgradeRecipe(recipeExporter, ModItems.DIAMOND_ROD, RecipeCategory.MISC, ModItems.NETHERITE_ROD);
        offerNetheriteUpgradeRecipe(recipeExporter, ModItems.DIAMOND_MACE, RecipeCategory.MISC, ModItems.NETHERITE_MACE);
        offerNetheriteUpgradeRecipe(recipeExporter, ModItems.DIAMOND_FIRE_MACE, RecipeCategory.MISC, ModItems.NETHERITE_FIRE_MACE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.IRON_MACE)
                .pattern("   ")
                .pattern(" h ")
                .pattern(" r ")
                .input('h', Items.HEAVY_CORE)
                .input('r', ModItems.IRON_ROD)
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(ModItems.IRON_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DIAMOND_MACE)
                .pattern("   ")
                .pattern(" h ")
                .pattern(" r ")
                .input('h', Items.HEAVY_CORE)
                .input('r', ModItems.DIAMOND_ROD)
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(ModItems.DIAMOND_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.EMERALD_MACE)
                .pattern("   ")
                .pattern(" h ")
                .pattern(" r ")
                .input('h', Items.HEAVY_CORE)
                .input('r', ModItems.EMERALD_ROD)
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(ModItems.EMERALD_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NETHERITE_MACE)
                .pattern("   ")
                .pattern(" h ")
                .pattern(" r ")
                .input('h', Items.HEAVY_CORE)
                .input('r', ModItems.NETHERITE_ROD)
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(ModItems.NETHERITE_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FIRE_MACE)
                .pattern("bbb")
                .pattern("bmb")
                .pattern("bbb")
                .input('m', Items.MACE)
                .input('b', Items.BLAZE_POWDER)
                .criterion(hasItem(Items.MACE), conditionsFromItem(ModItems.FIRE_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.IRON_FIRE_MACE)
                .pattern("bbb")
                .pattern("bmb")
                .pattern("bbb")
                .input('m', ModItems.IRON_MACE)
                .input('b', Items.BLAZE_POWDER)
                .criterion(hasItem(ModItems.IRON_MACE), conditionsFromItem(ModItems.IRON_FIRE_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DIAMOND_FIRE_MACE)
                .pattern("bbb")
                .pattern("bmb")
                .pattern("bbb")
                .input('m', ModItems.DIAMOND_MACE)
                .input('b', Items.BLAZE_POWDER)
                .criterion(hasItem(ModItems.DIAMOND_MACE), conditionsFromItem(ModItems.DIAMOND_FIRE_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.EMERALD_FIRE_MACE)
                .pattern("bbb")
                .pattern("bmb")
                .pattern("bbb")
                .input('m', ModItems.EMERALD_MACE)
                .input('b', Items.BLAZE_POWDER)
                .criterion(hasItem(ModItems.EMERALD_MACE), conditionsFromItem(ModItems.EMERALD_FIRE_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.NETHERITE_FIRE_MACE)
                .pattern("bbb")
                .pattern("bmb")
                .pattern("bbb")
                .input('m', ModItems.NETHERITE_MACE)
                .input('b', Items.BLAZE_POWDER)
                .criterion(hasItem(ModItems.NETHERITE_MACE), conditionsFromItem(ModItems.NETHERITE_FIRE_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FABULOUS_MACE)
                .pattern("bhb")
                .pattern("ied")
                .pattern("BnB")
                .input('h', Items.HEAVY_CORE)
                .input('b', Items.BLAZE_POWDER)
                .input('i', ModItems.IRON_ROD)
                .input('d', ModItems.DIAMOND_ROD)
                .input('B', Items.BREEZE_ROD)
                .input('n', ModItems.NETHERITE_ROD)
                .input('e', ModItems.EMERALD_ROD)
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(ModItems.IRON_MACE))
                .offerTo(recipeExporter);

    }
}
