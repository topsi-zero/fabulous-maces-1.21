package net.topsi.fabulousmaces;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import net.topsi.fabulousmaces.block.ModBlocks;
import net.topsi.fabulousmaces.enitity.ModEntities;
import net.topsi.fabulousmaces.item.ModItemGroups;
import net.topsi.fabulousmaces.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabulousMaces implements ModInitializer {
	public static final String MOD_ID = "fabulousmaces";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();

		LOGGER.info("Registered all for" + MOD_ID);
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
