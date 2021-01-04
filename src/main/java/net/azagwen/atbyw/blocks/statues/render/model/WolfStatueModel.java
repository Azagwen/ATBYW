package net.azagwen.atbyw.blocks.statues.render.model;

import com.google.common.collect.ImmutableList;
import net.azagwen.atbyw.blocks.statues.render.StatueRenderUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class WolfStatueModel extends EntityModel {
    private final ModelPart head_bone;
    private final ModelPart head_cubes;
    private final ModelPart torso;
    private final ModelPart rightBackLeg;
    private final ModelPart leftBackLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;
    private final ModelPart neck;

    public WolfStatueModel() {
        StatueRenderUtils utils = new StatueRenderUtils();

        this.head_bone = new ModelPart(this, 0, 0);
        this.head_bone.setPivot(-1.0F, 13.5F, -7.0F);
        this.head_cubes = new ModelPart(this, 0, 0);
        //Head Cube
        this.head_cubes.addCuboid(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, 0.0F);
        //Ear Cubes
        this.head_cubes.setTextureOffset(16, 14).addCuboid(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.head_cubes.setTextureOffset(16, 14).addCuboid(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        //Snout Cube
        this.head_cubes.setTextureOffset(0, 10).addCuboid(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F, 0.0F);
        this.head_bone.addChild(this.head_cubes);
        this.torso = new ModelPart(this, 18, 14);
        this.torso.addCuboid(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, 0.0F);
        this.torso.setPivot(0.0F, 14.0F, 2.0F);
        utils.setAngle(this.torso, 90.F, 0.0F, 0.0F);
        this.neck = new ModelPart(this, 21, 0);
        this.neck.addCuboid(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, 0.0F);
        this.neck.setPivot(-1.0F, 14.0F, -3.0F);
        utils.setAngle(this.neck, 90.F, 0.0F, 0.0F);
        this.rightBackLeg = new ModelPart(this, 0, 18);
        this.rightBackLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.rightBackLeg.setPivot(-2.5F, 16.0F, 7.0F);
        this.leftBackLeg = new ModelPart(this, 0, 18);
        this.leftBackLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.leftBackLeg.setPivot(0.5F, 16.0F, 7.0F);
        this.rightFrontLeg = new ModelPart(this, 0, 18);
        this.rightFrontLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.rightFrontLeg.setPivot(-2.5F, 16.0F, -4.0F);
        this.leftFrontLeg = new ModelPart(this, 0, 18);
        this.leftFrontLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.leftFrontLeg.setPivot(0.5F, 16.0F, -4.0F);
        this.tail = new ModelPart(this, 9, 18);
        this.tail.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.tail.setPivot(-1.0F, 12.0F, 8.0F);
        utils.setAngle(this.tail, 36.0F, 0.0F, 0.0F);
    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head_bone);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.torso, this.rightBackLeg, this.leftBackLeg, this.rightFrontLeg, this.leftFrontLeg, this.tail, this.neck);
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
