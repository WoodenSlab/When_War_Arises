package net.Wooden_Slab.when_war_arises.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.Wooden_Slab.when_war_arises.When_War_Arises;
import net.Wooden_Slab.when_war_arises.client.model.ModModelLayers;
import net.Wooden_Slab.when_war_arises.client.model.TaserLightningModel;
import net.Wooden_Slab.when_war_arises.entity.custom.TaserLightningEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class TaserLightningRenderer extends EntityRenderer<TaserLightningEntity> {
    private final TaserLightningModel<TaserLightningEntity> model;
    private final ResourceLocation TEXTURE = new ResourceLocation(When_War_Arises.MOD_ID, "textures/entity/taser_lightning.png");

    public TaserLightningRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TaserLightningModel<>(context.bakeLayer(ModModelLayers.TASER_LIGHTNING));
    }

    @Override
    public void render(TaserLightningEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(-entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));

        poseStack.translate(0.0D, 0.0625D, 0.0D);

        VertexConsumer vertex = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        model.renderToBuffer(poseStack, vertex, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, buffer, light);
    }

    @Override
    public ResourceLocation getTextureLocation(TaserLightningEntity entity) {
        return TEXTURE;
    }
}