package net.topsi.fabulousmaces.enitity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradedItem;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MaceTraderEntity extends MerchantEntity {


    public MaceTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void afterUsing(TradeOffer offer) {
    }

    @Override
    public boolean canRefreshTrades() {
        return false;
    }


    @Override
    protected void fillRecipes() {
        TradeOfferList offers = this.getOffers();

        offers.add(
                new TradeOffer(
                        new TradedItem(Items.TRIAL_KEY, 5),
                        new ItemStack(Items.HEAVY_CORE),
                        1,
                        10,
                        0.05f
                )
        );
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean canLeashAttachTo() {
        return false;
    }

}
