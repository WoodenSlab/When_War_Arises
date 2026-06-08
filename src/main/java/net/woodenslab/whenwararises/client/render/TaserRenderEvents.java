package net.woodenslab.whenwararises.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraftforge.fml.common.Mod;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.init.ModItems;

@Mod.EventBusSubscriber(modid = WhenWarArises.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TaserRenderEvents {

    @SubscribeEvent
    public static void onCameraShake(ViewportEvent.ComputeCameraAngles event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        ItemStack mainHandItem = player.getMainHandItem();
        if (!mainHandItem.is(ModItems.TASER_GUN.get())) return;

        double recoilCurve = getRecoilCurve(mainHandItem, player, (float) event.getPartialTick());

        if (recoilCurve > 0.0) {
            float cameraKickStrength = 1.5F;
            event.setPitch((float) (event.getPitch() - (recoilCurve * cameraKickStrength)));
        }
    }

    private static double getRecoilCurve(ItemStack stack, LocalPlayer player, float partialTicks) {
        if (stack.hasTag() && stack.getTag().contains("LastFiredTime")) {
            long lastFired = stack.getTag().getLong("LastFiredTime");
            float ticksSinceFired = (player.level().getGameTime() - lastFired) + partialTicks;
            float animationDuration = 10.0F;

            if (ticksSinceFired <= animationDuration) {
                float progress = Math.min(ticksSinceFired, animationDuration) / animationDuration;
                return Math.sin(progress * Math.PI);
            }
        }
        return 0.0;
    }

    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        ItemStack mainHandItem = player.getMainHandItem();

        if (event.getHand() == InteractionHand.OFF_HAND) {
            ItemStack offHandItem = player.getOffhandItem();
            if (mainHandItem.is(ModItems.TASER_GUN.get()) || offHandItem.is(ModItems.TASER_GUN.get())) {
                event.setCanceled(true);
                return;
            }
        }
        if (!mainHandItem.is(ModItems.TASER_GUN.get())) return;
        event.setCanceled(true);

        PoseStack poseStack = event.getPoseStack();
        PlayerRenderer renderer = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(player);

        poseStack.pushPose();

        renderFireAnimation(event, mainHandItem, player, poseStack);

        renderTaserGunItem(event, poseStack, player, mainHandItem);

        renderArm(renderer, player, poseStack, (MultiBufferSource.BufferSource) event.getMultiBufferSource(), event.getPackedLight(), HumanoidArm.LEFT);
        renderArm(renderer, player, poseStack, (MultiBufferSource.BufferSource) event.getMultiBufferSource(), event.getPackedLight(), HumanoidArm.RIGHT);

        poseStack.popPose();
    }

    private static void renderFireAnimation(RenderHandEvent event, ItemStack mainHandItem, LocalPlayer player, PoseStack poseStack) {
        double recoilCurve = getRecoilCurve(mainHandItem, player, event.getPartialTick());

        if (recoilCurve > 0.0) {
            poseStack.translate(0.0F, 0.0F, recoilCurve * 0.25F);

            float recoilStrength = 10F;
            poseStack.mulPose(Axis.XP.rotationDegrees((float) recoilCurve * recoilStrength));
        }
    }

    private static void renderTaserGunItem(RenderHandEvent event, PoseStack poseStack, LocalPlayer player, ItemStack mainHandItem) {
        poseStack.pushPose();

        poseStack.translate(0.56F, -0.53F, -0.72F);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                player,
                mainHandItem,
                ItemDisplayContext.FIRST_PERSON_RIGHT_HAND,
                false,
                poseStack,
                event.getMultiBufferSource(),
                player.level(),
                event.getPackedLight(),
                OverlayTexture.NO_OVERLAY,
                player.getId()
        );
        poseStack.popPose();
    }

    private void renderArm(PlayerRenderer renderer, LocalPlayer player, PoseStack poseStack, MultiBufferSource.BufferSource buffers, int light, HumanoidArm arm) {
        poseStack.pushPose();

        poseStack.translate(0.0F, -0.4F, -0.05);

        float yRotation = (arm == HumanoidArm.RIGHT) ? -172.5F : 150.0F;
        if (player.getMainArm() != HumanoidArm.RIGHT) {
            yRotation = (arm == HumanoidArm.RIGHT) ? -157.5F : 165.0F;
        }
        float zRotation = (arm == HumanoidArm.RIGHT) ? -21.0F : 22.5F;
        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(yRotation));
        poseStack.mulPose(Axis.ZP.rotationDegrees(zRotation));

        if (arm == HumanoidArm.RIGHT) {
            renderer.renderRightHand(poseStack, buffers, light, player);
        } else {
            renderer.renderLeftHand(poseStack, buffers, light, player);
        }
        poseStack.popPose();
    }
}
