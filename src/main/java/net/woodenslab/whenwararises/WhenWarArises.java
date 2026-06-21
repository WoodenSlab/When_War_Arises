package net.woodenslab.whenwararises;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.woodenslab.whenwararises.client.ClientHandler;
import net.woodenslab.whenwararises.client.render.TaserRenderEvents;
import net.woodenslab.whenwararises.init.*;
import org.slf4j.Logger;

@Mod(WhenWarArises.MOD_ID)
public class WhenWarArises {
    public static final String MOD_ID = "whenwararises";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WhenWarArises(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModSounds.register(modEventBus);
        ModParticleTypes.register(modEventBus);
        ModEffects.register(modEventBus);

        modEventBus.addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.register(new TaserRenderEvents());
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setupClient);
    }
}
