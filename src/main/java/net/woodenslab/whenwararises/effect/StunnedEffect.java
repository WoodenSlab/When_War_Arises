package net.woodenslab.whenwararises.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class StunnedEffect extends MobEffect {

    private static final UUID SLOW_MODIFIER_UUID = UUID.fromString("7e2c1a3e-1f0a-4b8e-9a3a-9e9f8f6e6b71");
    private static final UUID WEAKNESS_MODIFIER_UUID = UUID.fromString("4a8f3b2d-6c1e-4d9a-9f7a-2b8e7d6f5a3c");

    public StunnedEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, SLOW_MODIFIER_UUID.toString(),
                -0.15D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, WEAKNESS_MODIFIER_UUID.toString(),
                -4.0D, AttributeModifier.Operation.ADDITION);
    }
}
