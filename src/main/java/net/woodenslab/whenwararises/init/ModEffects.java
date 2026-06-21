package net.woodenslab.whenwararises.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.effect.StunnedEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, WhenWarArises.MOD_ID);

    public static RegistryObject<MobEffect> STUNNED_EFFECT = MOB_EFFECT.register("stunned",
            () -> new StunnedEffect(MobEffectCategory.NEUTRAL, 0x2F2F2F));

    public static void register(IEventBus eventBus) {
        MOB_EFFECT.register(eventBus);
    }
}
