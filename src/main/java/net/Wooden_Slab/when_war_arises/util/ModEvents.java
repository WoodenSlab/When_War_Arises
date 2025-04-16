package net.Wooden_Slab.when_war_arises.util;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(ModEvents.class);
    }
    
}