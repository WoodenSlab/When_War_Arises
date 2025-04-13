package net.Wooden_Slab.when_war_arises.entity;

import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.Wooden_Slab.when_war_arises.entity.custom.TaserLightningEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, When_War_Arises.MOD_ID);

    public static final RegistryObject<EntityType<TaserLightningEntity>> TASER_LIGHTNING =
            ENTITY_TYPE.register("taser_lightning", () -> EntityType.Builder.<TaserLightningEntity>of(TaserLightningEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build("taser_lightning"));


    public static void register(IEventBus eventBus) { ENTITY_TYPE.register(eventBus); }
}