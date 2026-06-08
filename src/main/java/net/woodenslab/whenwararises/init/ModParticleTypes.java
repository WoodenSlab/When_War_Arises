package net.woodenslab.whenwararises.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.woodenslab.whenwararises.WhenWarArises;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WhenWarArises.MOD_ID);

    public static final RegistryObject<SimpleParticleType> TASER_TRAIL =
            PARTICLES.register("taser_tracer", () -> new SimpleParticleType(true));

    public static void register (IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
