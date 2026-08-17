package net.topsi.fabulousmaces.enitity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.topsi.fabulousmaces.FabulousMaces;
import net.topsi.fabulousmaces.enitity.custom.MaceTraderEntity;

public class ModEntities {

    public static final EntityType<MaceTraderEntity> CUSTOM_TRADER =
            registerEntity(
                    "custom_trader",
                    EntityType.Builder.create(
                            MaceTraderEntity::new,
                            SpawnGroup.CREATURE
                    ).dimensions(1F, 2F)
            );



    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(FabulousMaces.MOD_ID, name), builder.build()
        );
    }


    public static void registerModEntities() {
        FabulousMaces.LOGGER.info("Registering Mod Entities for " + FabulousMaces.MOD_ID);

    }
}
