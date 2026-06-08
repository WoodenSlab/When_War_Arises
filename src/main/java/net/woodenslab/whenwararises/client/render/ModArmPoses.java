package net.woodenslab.whenwararises.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.woodenslab.whenwararises.WhenWarArises;

@Mod.EventBusSubscriber(modid = WhenWarArises.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModArmPoses {

    public static final HumanoidModel.ArmPose TASER_GUN_FIRE = HumanoidModel.ArmPose.create("TASER_GUN_FIRE", true, (model, entity, arm) -> {
        ModelPart pRightArm = model.rightArm;
        ModelPart pLeftArm = model.leftArm;
        ModelPart pHead = model.head;
        boolean pRightHanded = entity.getMainArm() == HumanoidArm.RIGHT;

        ModelPart modelpart = pRightHanded ? pRightArm : pLeftArm;
        ModelPart modelpart1 = pRightHanded ? pLeftArm : pRightArm;
        modelpart.yRot = (pRightHanded ? -0.3F : 0.3F) + pHead.yRot;
        modelpart1.yRot = (pRightHanded ? 0.6F : -0.6F) + pHead.yRot;
        modelpart.xRot = (-(float)Math.PI / 2F) + pHead.xRot + 0.1F;
        modelpart1.xRot = -1.5F + pHead.xRot;

        ItemStack stack = entity.getMainHandItem();
        if (!stack.hasTag() || !stack.getTag().contains("LastFiredTime")) return;

        long lastFired = stack.getTag().getLong("LastFiredTime");
        float partialTicks = Minecraft.getInstance().getFrameTime();
        float ticksSinceFired = (entity.level().getGameTime() - lastFired) + partialTicks;

        float animationDuration = 10.0F;
        float progress = Math.min(ticksSinceFired, animationDuration) / animationDuration;
        double recoilCurve = Math.sin(progress * Math.PI);

        float recoilStrength = 0.8F;
        float recoilKick = (float) (recoilCurve * recoilStrength);

        model.rightArm.xRot -= recoilKick;
        model.leftArm.xRot -= recoilKick;
    });
}
