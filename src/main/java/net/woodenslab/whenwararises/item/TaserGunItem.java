package net.woodenslab.whenwararises.item;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.client.render.ModArmPoses;
import net.woodenslab.whenwararises.init.ModEffects;
import net.woodenslab.whenwararises.init.ModItems;
import net.woodenslab.whenwararises.init.ModParticleTypes;
import net.woodenslab.whenwararises.init.ModSounds;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class TaserGunItem extends Item {
    public static final ResourceLocation FIRING = new ResourceLocation(WhenWarArises.MOD_ID, "fire");

    public static final ClampedItemPropertyFunction FIRING_PROPERTY =
            (stack, level, entity, seed) -> {
                if (level != null && stack.hasTag() && stack.getTag().contains("LastFiredTime")) {
                    long ticksSinceFired = level.getGameTime() - stack.getTag().getLong("LastFiredTime");
                    if (ticksSinceFired < 10) {
                        return 1.0F;
                    }
                }
                if (stack.getTag() != null) stack.getTag().remove("LastFiredTime");
                return 0.0F;
            };

    public TaserGunItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (itemStack.hasTag() && itemStack.getTag().contains("LastFiredTime")) {
                    long lastFired = itemStack.getTag().getLong("LastFiredTime");
                    long timeAlive = entityLiving.level().getGameTime() - lastFired;
                    
                    if (timeAlive >= 0 && timeAlive <= 10) {
                        return ModArmPoses.TASER_GUN_FIRE;
                    }
                }

                if (hand == InteractionHand.MAIN_HAND) {
                    return HumanoidModel.ArmPose.CROSSBOW_HOLD;
                }
                return HumanoidModel.ArmPose.EMPTY;
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        CompoundTag tag = stack.getOrCreateTag();

        int remainingDurability = stack.getMaxDamage() - stack.getDamageValue();
        if (remainingDurability <= 1 || hand == InteractionHand.OFF_HAND) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide()) {
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.TASER_GUN_FIRE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        fireTracerParticle(level, player);
        tag.putLong("LastFiredTime", level.getGameTime());

        player.getCooldowns().addCooldown(this, 50);

        return super.use(level, player, hand);
    }

    private void fireTracerParticle(Level level, Player player) {
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {

            double range = 10.0;
            Vec3 eyePosition = player.getEyePosition();
            Vec3 lookVector = player.getViewVector(1.0F);
            Vec3 endPosition = eyePosition.add(lookVector.x * range, lookVector.y * range, lookVector.z * range);

            AABB searchBox = player.getBoundingBox().expandTowards(lookVector.scale(range)).inflate(1.0D, 1.0D, 1.0D);
            EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                    level, player, eyePosition, endPosition, searchBox, (entity) -> !entity.isSpectator() && entity.isPickable()
            );

            Vec3 actualHitPos;
            if (entityHit != null && entityHit.getEntity() instanceof LivingEntity target) {
                actualHitPos = target.getBoundingBox().getCenter();

                target.hurt(level.damageSources().mobAttack(player), 2.0F);
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30));
                target.addEffect(new MobEffectInstance(ModEffects.STUNNED_EFFECT.get(), 60));

            } else {
                HitResult blockHit = player.pick(range, 1.0F, false);
                actualHitPos = blockHit.getLocation();
            }

            spawnTracerParticle(serverLevel, player, actualHitPos);
        }
    }

    private void spawnTracerParticle(ServerLevel level, Player player, Vec3 targetPos) {
        Vec3 forward = player.getViewVector(1.0F);
        double forwardOffset = 0.8D;
        double verticalOffset = 0.25D;
        Vec3 startPos = player.getEyePosition()
                .add(forward.scale(forwardOffset))
                .add(forward.scale(verticalOffset));

        Vec3 direction = targetPos.subtract(startPos).normalize();
        double bulletSpeed = 2.5D;

        level.sendParticles(
                ModParticleTypes.TASER_TRAIL.get(),
                startPos.x, startPos.y, startPos.z,
                0,
                direction.x, direction.y, direction.z,
                bulletSpeed
        );
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repair) {
        return repair.is(ModItems.BATTERY.get());
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return true;
    }
}
