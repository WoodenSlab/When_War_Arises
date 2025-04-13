package net.Wooden_Slab.when_war_arises.item.custom;

import net.Wooden_Slab.when_war_arises.entity.ModEntities;
import net.Wooden_Slab.when_war_arises.entity.custom.TaserLightningEntity;
import net.Wooden_Slab.when_war_arises.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class TaserGunItem extends Item {
    public TaserGunItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repair) {
        return repair.is(ModItems.BATTERY.get());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        int remainingDurability = stack.getMaxDamage() - stack.getDamageValue();

        if (remainingDurability <= 1) {
            return InteractionResultHolder.fail(stack);
        }


        if (!level.isClientSide) {
            TaserLightningEntity taserEntity = new TaserLightningEntity(ModEntities.TASER_LIGHTNING.get(), level);

            // Initial position (eyes)
            taserEntity.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());

            Vec3 look = player.getLookAngle();

            taserEntity.setDeltaMovement(look.scale(2.0)); // Speed

            // Apply the player's rotation
            float yaw = player.getYRot();
            float pitch = player.getXRot();
            taserEntity.setXRot(pitch);
            taserEntity.setYRot(yaw);

            taserEntity.yRotO = yaw;
            taserEntity.xRotO = pitch;

            taserEntity.setOwner(player);

            // Spawn the entity
            level.addFreshEntity(taserEntity);
        }

        // Cooldown
        player.getCooldowns().addCooldown(this, 100);

        // Sound
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);

        // Remove durability
        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}