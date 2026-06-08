package net.woodenslab.whenwararises.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.item.RiotShieldItem;
import net.woodenslab.whenwararises.item.TaserGunItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WhenWarArises.MOD_ID);

    public static final RegistryObject<Item> RIOT_SHIELD = ITEMS.register("riot_shield",
            () -> new RiotShieldItem(new Item.Properties()
                    .durability(750)
            ));

    public static final RegistryObject<Item> HAPPY_RIOT_SHIELD = ITEMS.register("happy_riot_shield",
            () -> new RiotShieldItem(new Item.Properties()
                    .durability(750)
            ));

    public static final RegistryObject<Item> FUNNY_RIOT_SHIELD = ITEMS.register("funny_riot_shield",
            () -> new RiotShieldItem(new Item.Properties()
                    .durability(750)
            ));

    public static final RegistryObject<Item> TASER_GUN = ITEMS.register("taser_gun",
            () -> new TaserGunItem(new Item.Properties()
                    .durability(16)
            ));

    public static final RegistryObject<Item> BATTERY = ITEMS.register("battery",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
