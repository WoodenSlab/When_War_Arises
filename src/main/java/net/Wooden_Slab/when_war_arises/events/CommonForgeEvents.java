package net.Wooden_Slab.when_war_arises.events;

import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.Wooden_Slab.when_war_arises.item.ModItems;
import net.Wooden_Slab.when_war_arises.item.custom.TaserGunItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = When_War_Arises.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (left.getItem() instanceof TaserGunItem && right.is(ModItems.BATTERY.get())) {
            int damage = left.getDamageValue();
            int batteries = right.getCount();
            int repairAmount = Math.min(damage, batteries);

            if (repairAmount > 0) {
                ItemStack output = left.copy();
                output.setDamageValue(damage - repairAmount);

                event.setOutput(output);
                event.setMaterialCost(repairAmount);
                event.setCost(1);
            } else {
                event.setOutput(ItemStack.EMPTY);
            }
        }
    }
}