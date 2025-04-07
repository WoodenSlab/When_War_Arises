package net.Wooden_Slab.when_war_arises.item;

import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, When_War_Arises.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WHEN_WAR_ARISES_TAB = CREATIVE_MODE_TABS.register("when_war_arises_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RIOT_SHIELD.get()))
                    .title(Component.translatable("creativetab.when_war_arises_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RIOT_SHIELD.get());
                        output.accept(ModItems.HAPPY_RIOT_SHIELD.get());
                        output.accept(ModItems.FUNNY_RIOT_SHIELD.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }



}
