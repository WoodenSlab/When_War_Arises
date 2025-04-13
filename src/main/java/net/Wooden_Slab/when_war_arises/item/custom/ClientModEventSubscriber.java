package net.Wooden_Slab.when_war_arises.item.custom;

import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.Wooden_Slab.when_war_arises.client.model.ModModelLayers;
import net.Wooden_Slab.when_war_arises.client.model.TaserLightningModel;
import net.Wooden_Slab.when_war_arises.client.renderer.entity.TaserLightningRenderer;
import net.Wooden_Slab.when_war_arises.entity.ModEntities;
import net.Wooden_Slab.when_war_arises.item.ModItems;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.TASER_LIGHTNING, TaserLightningModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.TASER_LIGHTNING.get(), TaserLightningRenderer::new);
    }
}