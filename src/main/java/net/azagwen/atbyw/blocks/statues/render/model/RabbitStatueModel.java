package net.azagwen.atbyw.blocks.statues.render.model;

import com.google.common.collect.ImmutableList;
import net.azagwen.atbyw.blocks.statues.render.StatueRenderUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.RabbitEntity;

@Environment(EnvType.CLIENT)
public class RabbitStatueModel extends EntityModel {
    private final ModelPart leftFoot = new ModelPart(this, 26, 24);
    private final ModelPart rightFoot;
    private final ModelPart leftBackLeg;
    private final ModelPart rightBackLeg;
    private final ModelPart torso;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart head;
    private final ModelPart rightEar;
    private final ModelPart leftEar;
    private final ModelPart tail;
    private final ModelPart nose;

    public RabbitStatueModel() {
        StatueRenderUtils utils = new StatueRenderUtils();

        this.leftFoot.addCuboid(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F);
        this.leftFoot.setPivot(3.0F, 17.5F, 3.7F);
        this.leftFoot.mirror = true;
        utils.setAngle(this.leftFoot, 0.0F, 0.0F, 0.0F);
        this.rightFoot = new ModelPart(this, 8, 24);
        this.rightFoot.addCuboid(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F);
        this.rightFoot.setPivot(-3.0F, 17.5F, 3.7F);
        this.rightFoot.mirror = true;
        utils.setAngle(this.rightFoot, 0.0F, 0.0F, 0.0F);
        this.leftBackLeg = new ModelPart(this, 30, 15);
        this.leftBackLeg.addCuboid(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F);
        this.leftBackLeg.setPivot(3.0F, 17.5F, 3.7F);
        this.leftBackLeg.mirror = true;
        utils.setAngle(this.leftBackLeg, -20.0F, 0.0F, 0.0F);
        this.rightBackLeg = new ModelPart(this, 16, 15);
        this.rightBackLeg.addCuboid(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F);
        this.rightBackLeg.setPivot(-3.0F, 17.5F, 3.7F);
        this.rightBackLeg.mirror = true;
        utils.setAngle(this.rightBackLeg, -20.0F, 0.0F, 0.0F);
        this.torso = new ModelPart(this, 0, 0);
        this.torso.addCuboid(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F);
        this.torso.setPivot(0.0F, 19.0F, 8.0F);
        this.torso.mirror = true;
        utils.setAngle(this.torso, -20.0F, 0.0F, 0.0F);
        this.leftFrontLeg = new ModelPart(this, 8, 15);
        this.leftFrontLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F);
        this.leftFrontLeg.setPivot(3.0F, 17.0F, -1.0F);
        this.leftFrontLeg.mirror = true;
        utils.setAngle(this.leftFrontLeg, -10.0F, 0.0F, 0.0F);
        this.rightFrontLeg = new ModelPart(this, 0, 15);
        this.rightFrontLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F);
        this.rightFrontLeg.setPivot(-3.0F, 17.0F, -1.0F);
        this.rightFrontLeg.mirror = true;
        utils.setAngle(this.rightFrontLeg, -10.0F, 0.0F, 0.0F);
        this.head = new ModelPart(this, 32, 0);
        this.head.addCuboid(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F);
        this.head.setPivot(0.0F, 16.0F, -1.0F);
        this.head.mirror = true;
        utils.setAngle(this.head, 0.0F, 0.0F, 0.0F);
        this.rightEar = new ModelPart(this, 52, 0);
        this.rightEar.addCuboid(-2.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F);
        this.rightEar.setPivot(0.0F, 16.0F, -1.0F);
        this.rightEar.mirror = true;
        utils.setAngle(this.rightEar, 0.0F, -15.0F, 0.0F);
        this.leftEar = new ModelPart(this, 58, 0);
        this.leftEar.addCuboid(0.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F);
        this.leftEar.setPivot(0.0F, 16.0F, -1.0F);
        this.leftEar.mirror = true;
        utils.setAngle(this.leftEar, 0.0F, 15.0F, 0.0F);
        this.tail = new ModelPart(this, 52, 6);
        this.tail.addCuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F);
        this.tail.setPivot(0.0F, 20.0F, 7.0F);
        this.tail.mirror = true;
        utils.setAngle(this.tail, -20.0F, 0.0F, 0.0F);
        this.nose = new ModelPart(this, 32, 9);
        this.nose.addCuboid(-0.5F, -2.5F, -5.5F, 1.0F, 1.0F, 1.0F);
        this.nose.setPivot(0.0F, 16.0F, -1.0F);
        this.nose.mirror = true;
        utils.setAngle(this.nose, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.push();
        matrices.scale(1.0F, 1.0F, 1.0F);
        matrices.translate(0.0D, 0.0D, 0.0D);
        ImmutableList.of(this.leftFoot, this.rightFoot, this.leftBackLeg, this.rightBackLeg, this.torso, this.leftFrontLeg, this.rightFrontLeg, this.head, this.rightEar, this.leftEar, this.tail, this.nose, new ModelPart[0]).forEach((modelPart) -> {
            modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        matrices.pop();

    }
}
