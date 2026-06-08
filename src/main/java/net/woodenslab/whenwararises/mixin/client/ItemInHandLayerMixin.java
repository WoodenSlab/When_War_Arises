package net.woodenslab.whenwararises.mixin.client;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import net.woodenslab.whenwararises.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;

@Mixin(ItemInHandLayer.class)
public class ItemInHandLayerMixin {

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void hideOffhandHoldingTaserGun(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext displayContext, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        HumanoidArm offhandArm = livingEntity.getMainArm().getOpposite();

        if (arm == offhandArm) {
            ItemStack mainHandItem = livingEntity.getMainHandItem();
            ItemStack offHandItem = livingEntity.getOffhandItem();

            if (mainHandItem.is(ModItems.TASER_GUN.get())) {
                ci.cancel();
            } else if (offHandItem.is(ModItems.TASER_GUN.get())) {
                ci.cancel();
            }
        }
    }
}
