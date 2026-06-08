package net.woodenslab.whenwararises.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.woodenslab.whenwararises.WhenWarArises;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WhenWarArises.MOD_ID);

    public static final RegistryObject<SoundEvent> TASER_GUN_FIRE = registerSoundEvent("taser_gun_fire");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(WhenWarArises.MOD_ID, name)));
    }

    public static void register (IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);
    }
}
