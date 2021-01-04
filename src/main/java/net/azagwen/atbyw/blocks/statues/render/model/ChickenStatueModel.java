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

@Environment(EnvType.CLIENT)
public class ChickenStatueModel extends EntityModel {
    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart beak;
    private final ModelPart wattle;

    public ChickenStatueModel() {
        StatueRenderUtils util = new StatueRenderUtils();

        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, 0.0F);
        this.head.setPivot(0.0F, 15.0F, -4.0F);
        this.beak = new ModelPart(this, 14, 0);
        this.beak.addCuboid(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, 0.0F);
        this.beak.setPivot(0.0F, 15.0F, -4.0F);
        this.wattle = new ModelPart(this, 14, 4);
        this.wattle.addCuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, 0.0F);
        this.wattle.setPivot(0.0F, 15.0F, -4.0F);
        this.torso = new ModelPart(this, 0, 9);
        this.torso.addCuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F);
        this.torso.setPivot(0.0F, 16.0F, 0.0F);
        util.setAngle(this.torso, 90.0F, 0.0F, 0.0F);
        this.rightLeg = new ModelPart(this, 26, 0);
        this.rightLeg.addCuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
        this.rightLeg.setPivot(-2.0F, 19.0F, 1.0F);
        this.leftLeg = new ModelPart(this, 26, 0);
        this.leftLeg.addCuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
        this.leftLeg.setPivot(1.0F, 19.0F, 1.0F);
        this.rightWing = new ModelPart(this, 24, 13);
        this.rightWing.addCuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F);
        this.rightWing.setPivot(-4.0F, 13.0F, 0.0F);
        this.leftWing = new ModelPart(this, 24, 13);
        this.leftWing.addCuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F);
        this.leftWing.setPivot(4.0F, 13.0F, 0.0F);
    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head, this.beak, this.wattle);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.torso, this.rightLeg, this.leftLeg, this.rightWing, this.leftWing);
    }

    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.push();
        matrices.scale(1.0F, 1.0F, 1.0F);
        matrices.translate(0.0D, 0.0D, 0.0D);
        this.getHeadParts().forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha));
        this.getBodyParts().forEach((modelPart) -> modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha));
        matrices.pop();
    }
}
