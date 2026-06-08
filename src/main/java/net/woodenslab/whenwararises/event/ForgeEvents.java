package net.woodenslab.whenwararises.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.item.TaserGunItem;

@Mod.EventBusSubscriber(modid = WhenWarArises.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void onOffhandInteract(PlayerInteractEvent event) {
        if (event.getHand() == InteractionHand.OFF_HAND) {
            Player player = event.getEntity();
            if (player == null) return;

            if (player.getMainHandItem().getItem() instanceof TaserGunItem) {
                if (event.isCancelable()) {
                    event.setCanceled(true);
                }
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
