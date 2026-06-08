package net.woodenslab.whenwararises.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.init.ModParticleTypes;
import net.woodenslab.whenwararises.particle.TaserTrailParticle;

@Mod.EventBusSubscriber(modid = WhenWarArises.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.TASER_TRAIL.get(), TaserTrailParticle.Provider::new);
    }
}
