package net.woodenslab.whenwararises.client.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.item.TaserGunItem;

@Mod.EventBusSubscriber(modid = WhenWarArises.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {

    @SubscribeEvent
    public static void onMouseInput(InputEvent.InteractionKeyMappingTriggered event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (mc.player.getMainHandItem().getItem() instanceof TaserGunItem) {
            if (event.isAttack()) {
                event.setCanceled(true);
                event.setSwingHand(false);
            }
        }
    }
}
