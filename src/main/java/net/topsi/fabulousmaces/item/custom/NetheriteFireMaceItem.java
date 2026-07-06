package net.topsi.fabulousmaces.item.custom;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.topsi.fabulousmaces.item.ModItems;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.particle.ParticleTypes;

public class NetheriteFireMaceItem extends Item {

    private static final float ATTACK_DAMAGE = 5.0F;
    private static final float ATTACK_SPEED = -3.4F;

    private static final float EARLY_FALL = 4.0F;
    private static final float MIDDLE_FALL = 12.0F;
    private static final float LATE_FALL = 22.0F;

    private static final float MIN_FALL_DISTANCE = 1.5F;

    public static final float KNOCKBACK_RANGE = 3.5F;
    private static final float KNOCKBACK_POWER_VERTICAL = 0.7F;
    private static final float KNOCKBACK_POWER_HORIZONTAL = 0.7F;

    private static final int COOLDOWN_TICKS = 100;

    private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 3;
    private static final float ATTACK_SPEED_MODIFIER_VALUE = -3.4F;
    public static final float MINING_SPEED_MULTIPLIER = 1.5F;
    private static final float field_50141 = 5.0F;



    public NetheriteFireMaceItem(Settings settings) {
        super(settings);

    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, ATTACK_DAMAGE, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, ATTACK_SPEED, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayerEntity serverPlayerEntity && shouldDealAdditionalDamage(serverPlayerEntity)) {
            ServerWorld serverWorld = (ServerWorld)attacker.getWorld();
            if (serverPlayerEntity.shouldIgnoreFallDamageFromCurrentExplosion() && serverPlayerEntity.currentExplosionImpactPos != null) {
                if (serverPlayerEntity.currentExplosionImpactPos.y > serverPlayerEntity.getPos().y) {
                    serverPlayerEntity.currentExplosionImpactPos = serverPlayerEntity.getPos();
                }
            } else {
                serverPlayerEntity.currentExplosionImpactPos = serverPlayerEntity.getPos();
            }

            serverPlayerEntity.setIgnoreFallDamageFromCurrentExplosion(true);
            serverPlayerEntity.setVelocity(serverPlayerEntity.getVelocity().withAxis(Direction.Axis.Y, 0.01F));
            serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
            if (target.isOnGround()) {
                serverPlayerEntity.setSpawnExtraParticlesOnFall(true);
                SoundEvent soundEvent = serverPlayerEntity.fallDistance > 5.0F ? SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY : SoundEvents.ITEM_MACE_SMASH_GROUND;
                serverWorld.playSound(
                        null, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), soundEvent, serverPlayerEntity.getSoundCategory(), 1.0F, 1.0F
                );
            } else {
                serverWorld.playSound(
                        null,
                        serverPlayerEntity.getX(),
                        serverPlayerEntity.getY(),
                        serverPlayerEntity.getZ(),
                        SoundEvents.ITEM_MACE_SMASH_AIR,
                        serverPlayerEntity.getSoundCategory(),
                        1.0F,
                        1.0F
                );
            }
            target.setOnFireFor(10);

            knockbackNearbyEntities(serverWorld, serverPlayerEntity, target);
        }

        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        if (shouldDealAdditionalDamage(attacker)) {
            attacker.onLanding();
        }
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.BREEZE_ROD);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        if (damageSource.getSource() instanceof LivingEntity livingEntity) {
            if (!shouldDealAdditionalDamage(livingEntity)) {
                return 0.0F;
            } else {
                float f = 3.0F;
                float g = 8.0F;
                float h = livingEntity.fallDistance;
                float i;
                if (h <= 3.0F) {
                    i = EARLY_FALL * h;
                } else if (h <= 8.0F) {
                    i = MIDDLE_FALL + 2.0F * (h - 3.0F);
                } else {
                    i = LATE_FALL + h - 8.0F;
                }

                return livingEntity.getWorld() instanceof ServerWorld serverWorld
                        ? i + EnchantmentHelper.getSmashDamagePerFallenBlock(serverWorld, livingEntity.getWeaponStack(), target, damageSource, 0.0F) * h
                        : i;
            }
        } else {
            return 0.0F;
        }
    }

    private static void knockbackNearbyEntities(World world, PlayerEntity player, Entity attacked) {
        world.syncWorldEvent(WorldEvents.SMASH_ATTACK, attacked.getSteppingPos(), 750);
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand(KNOCKBACK_RANGE), getKnockbackPredicate(player, attacked)).forEach(entity -> {
            Vec3d vec3d = entity.getPos().subtract(attacked.getPos());
            double d = getKnockback(player, entity, vec3d);
            Vec3d vec3d2 = vec3d.normalize().multiply(d);
            if (d > 0.0) {
                entity.addVelocity(vec3d2.x, KNOCKBACK_POWER_HORIZONTAL, vec3d2.z);
                if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                    serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                }
            }
        });
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(PlayerEntity player, Entity attacked) {
        return entity -> {
            boolean bl = !entity.isSpectator();
            boolean bl2 = entity != player && entity != attacked;
            boolean bl3 = !player.isTeammate(entity);
            boolean bl4 = !(entity instanceof TameableEntity tameableEntity && tameableEntity.isTamed() && player.getUuid().equals(tameableEntity.getOwnerUuid()));
            boolean bl5 = !(entity instanceof ArmorStandEntity armorStandEntity && armorStandEntity.isMarker());
            boolean bl6 = attacked.squaredDistanceTo(entity) <= Math.pow(KNOCKBACK_RANGE, 2.0);
            return bl && bl2 && bl3 && bl4 && bl5 && bl6;
        };
    }

    private static double getKnockback(PlayerEntity player, LivingEntity attacked, Vec3d distance) {
        return (3.5 - distance.length())
                * KNOCKBACK_POWER_VERTICAL
                * (player.fallDistance > 5.0F ? 2 : 1)
                * (1.0 - attacked.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
    }

    public static boolean shouldDealAdditionalDamage(LivingEntity attacker) {
        return attacker.fallDistance > MIN_FALL_DISTANCE && !attacker.isFallFlying();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if (entity instanceof PlayerEntity player && !world.isClient()) {

            boolean hasMaceInHand =
                    player.getMainHandStack().isOf(ModItems.NETHERITE_FIRE_MACE) ||
                            player.getOffHandStack().isOf(ModItems.NETHERITE_FIRE_MACE);

            if (hasMaceInHand) {

                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.ABSORPTION,
                        40,
                        2,
                        true,
                        false,
                        true
                ));

                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.FIRE_RESISTANCE,
                        40,
                        0,
                        true,
                        false,
                        true
                ));

            }
        }


    }



    private static final HashMap<UUID, Integer> STOMP_TICKS = new HashMap<>();
    static {
        ServerTickEvents.END_WORLD_TICK.register(world -> {

            for (ServerPlayerEntity player : world.getPlayers()) {

                UUID id = player.getUuid();
                Integer ticksObj = STOMP_TICKS.get(id);

                if (ticksObj == null) continue;

                int ticks = ticksObj - 1;

                if (ticks > 0) {
                    STOMP_TICKS.put(id, ticks);
                    continue;
                }

                if (player.getVelocity().y > 0) {

                    Vec3d v = player.getVelocity();

                    player.setVelocity(v.x, -2.2, v.z); //stamp velocity
                    player.velocityModified = true;
                }

                // check for impact
                if (player.isOnGround()) {
                    STOMP_TICKS.remove(id);
                    performImpact(player);
                }
            }
        });
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, net.minecraft.entity.player.PlayerEntity player, Hand hand) {

        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

        if (serverPlayer.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        Vec3d vel = serverPlayer.getVelocity();

        // vertical velocity up
        serverPlayer.setVelocity(vel.x, 1, vel.z);
        serverPlayer.velocityModified = true;

        STOMP_TICKS.put(serverPlayer.getUuid(), 10);

        serverPlayer.getItemCooldownManager().set(this, COOLDOWN_TICKS);

        return TypedActionResult.success(stack);
    }

    private static void performImpact(ServerPlayerEntity player) {

        ServerWorld world = player.getServerWorld();

        double radius = 4.5;

        for (LivingEntity entity : world.getEntitiesByClass(
                LivingEntity.class,
                player.getBoundingBox().expand(radius),
                e -> e != player)) {

            entity.damage(world.getDamageSources().fall(), 6.0f);
            entity.setOnFireFor(5);

            Vec3d dir = entity.getPos().subtract(player.getPos()).normalize();
            entity.addVelocity(dir.x * 1.2, 0.6, dir.z * 1.2);
            entity.velocityModified = true;
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY,
                player.getSoundCategory(),
                1.2f,
                0.9f
        );
        // particles
        world.spawnParticles(
                ParticleTypes.CRIT,
                player.getX(),
                player.getY(),
                player.getZ(),
                40,
                1.5, 0.2, 1.5,
                0.2
        );

        world.spawnParticles(
                ParticleTypes.ASH,
                player.getX(),
                player.getY(),
                player.getZ(),
                60,
                1.5, 0.2, 1.5,
                0.2
        );

        world.spawnParticles(
                ParticleTypes.SMOKE,
                player.getX(),
                player.getY(),
                player.getZ(),
                60,
                1.5, 0.2, 1.5,
                0.2
        );

        world.spawnParticles(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                player.getX(),
                player.getY(),
                player.getZ(),
                60,
                1.5, 0.2, 1.5,
                0.2
        );

        world.spawnParticles(
                ParticleTypes.FLAME,
                player.getX(),
                player.getY(),
                player.getZ(),
                60,
                1.5, 0.2, 1.5,
                0.2
        );
    }
}
