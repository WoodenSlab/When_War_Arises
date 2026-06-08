package net.woodenslab.whenwararises.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.woodenslab.whenwararises.WhenWarArises;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WhenWarArises.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WHEN_WAR_ARISES_TAB = CREATIVE_MODE_TABS.register("when_war_arrises",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.whenwararises"))
                    .icon(() -> new ItemStack(ModItems.RIOT_SHIELD.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RIOT_SHIELD.get());
                        output.accept(ModItems.HAPPY_RIOT_SHIELD.get());
                        output.accept(ModItems.FUNNY_RIOT_SHIELD.get());
                        output.accept(ModItems.TASER_GUN.get());
                        output.accept(ModItems.BATTERY.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
