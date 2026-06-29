package net.topsi.fabulousmaces.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.MaceItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MaceItem.class)
public class MaceItemMixin {

    @Inject(method = "getBonusAttackDamage", at = @At("RETURN"), cancellable = true)
    private void nerfMace(Entity target,
                          float baseAttackDamage,
                          DamageSource damageSource,
                          CallbackInfoReturnable<Float> cir) {

        cir.setReturnValue(cir.getReturnValue() * 0.6F); // 40% nerf
    }
}