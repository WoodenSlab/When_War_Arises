package net.Wooden_Slab.when_war_arises.item.custom;

import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.Wooden_Slab.when_war_arises.item.ModItems;
import net.Wooden_Slab.when_war_arises.sound.ModSounds;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = When_War_Arises.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void registerItemProperties(EntityRenderersEvent.RegisterRenderers event) {
        ItemProperties.register(ModItems.RIOT_SHIELD.get(), new ResourceLocation("blocking"),
                (ClampedItemPropertyFunction) (stack, level, entity, seed) -> {
                    if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                        return 1.0F;
                    }
                    return 0.0F;
                });

        ItemProperties.register(ModItems.HAPPY_RIOT_SHIELD.get(), new ResourceLocation("blocking"),
                (ClampedItemPropertyFunction) (stack, level, entity, seed) -> {
                    if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                        return 1.0F;
                    }
                    return 0.0F;
                });

        ItemProperties.register(ModItems.FUNNY_RIOT_SHIELD.get(), new ResourceLocation("blocking"),
                (ClampedItemPropertyFunction) (stack, level, entity, seed) -> {
                    if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                        return 1.0F;
                    }
                    return 0.0F;
                });
    }
}