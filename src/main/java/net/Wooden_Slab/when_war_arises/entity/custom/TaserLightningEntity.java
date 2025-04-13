package net.Wooden_Slab.when_war_arises.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;


public class TaserLightningEntity extends Entity {
    public TaserLightningEntity(EntityType<? extends TaserLightningEntity> type, Level level) {
        super(type, level);
        this.refreshDimensions();
    }

    private Entity owner;

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public Entity getOwner() {
        return this.owner;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public void tick() {
        super.tick();

        this.move(MoverType.SELF, this.getDeltaMovement());

        // Collision with entity
        for (Entity entity : this.level().getEntities(this, this.getBoundingBox())) {

            // Avoid hitting yourself
            if (entity == this.getOwner()) return;

            // Apply effects on hit
            if (entity != this.getOwner() && entity instanceof LivingEntity living) {
                living.hurt(this.level().damageSources().thrown(this, this.getOwner()), 2.0F);
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
                living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
                this.discard();
                break;
            }
        }

        // Collision with block
        HitResult hitResult = this.level().clip(new ClipContext(
                this.position(),
                this.position().add(this.getDeltaMovement()),
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                this
        ));

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            this.discard();
            return;
        }

        // Despawns after 0.5s
        if (this.tickCount > 10) {
            this.discard();
        }
    }

    public void shootFromPlayer(Player player, float speed) {
        Vec3 direction = player.getLookAngle().normalize();
        this.setDeltaMovement(direction.scale(speed));
        this.setYRot(player.getYRot());
        this.setXRot(player.getXRot());
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(0.125F, 0.125F);
    }

}
