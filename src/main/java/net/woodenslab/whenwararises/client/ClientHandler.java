package net.woodenslab.whenwararises.client;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.init.ModItems;
import net.woodenslab.whenwararises.item.TaserGunItem;

import java.util.List;

@Mod.EventBusSubscriber(modid = WhenWarArises.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {

    private static final ResourceLocation BLOCKING = new ResourceLocation("blocking");

    private static final ClampedItemPropertyFunction BLOCKING_PROPERTY =
            (stack, level, entity, seed) -> {
                if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                    return 1.0F;
                }
                return 0.0F;
            };


    public static void setupClient() {
        List.of(
                ModItems.RIOT_SHIELD,
                ModItems.HAPPY_RIOT_SHIELD,
                ModItems.FUNNY_RIOT_SHIELD
        ).forEach(item -> ItemProperties.register(item.get(), BLOCKING, BLOCKING_PROPERTY));

        ItemProperties.register(ModItems.TASER_GUN.get(), TaserGunItem.FIRING, TaserGunItem.FIRING_PROPERTY);
    }
}
