package net.Wooden_Slab.when_war_arises.sound;

import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, When_War_Arises.MOD_ID);

    public static final RegistryObject<SoundEvent> DAMAGE_BLOCKED_BY_RIOT_SHIELD = registerSoundEvents("damage_blocked_by_riot_shield");



    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(When_War_Arises.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);

    }

}