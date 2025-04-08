package net.Wooden_Slab.when_war_arises.util;

import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.Wooden_Slab.when_war_arises.item.custom.RiotShieldItem;
import net.Wooden_Slab.when_war_arises.sound.ModSounds;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEvents {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(ModEvents.class);
    }


    @SubscribeEvent
    public static void onShieldBlock(ShieldBlockEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            ItemStack usingItem = player.getUseItem();

            if (usingItem.getItem() instanceof RiotShieldItem) {
                When_War_Arises.LOGGER.info("ShieldBlockEvent déclenché !");
                player.playSound(ModSounds.DAMAGE_BLOCKED_BY_RIOT_SHIELD.get(), 1.0F, 1.0F);
            }
        }
    }
}