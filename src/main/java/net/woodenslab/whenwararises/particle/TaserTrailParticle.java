package net.woodenslab.whenwararises.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;

public class TaserTrailParticle extends TextureSheetParticle {

    protected TaserTrailParticle(ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(level, x, y, z, velocityX, velocityY, velocityZ);

        this.xd = velocityX;
        this.yd = velocityY;
        this.zd = velocityZ;

        float rotation = (float) Math.toRadians(40);
        this.roll = rotation;
        this.oRoll = rotation;

        this.gravity = 0.0F;
        this.friction = 1.0F;
        this.scale(0.4F);
        this.hasPhysics = false;

        this.lifetime = 3;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return 240;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public net.minecraft.client.particle.Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            TaserTrailParticle particle = new TaserTrailParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}
