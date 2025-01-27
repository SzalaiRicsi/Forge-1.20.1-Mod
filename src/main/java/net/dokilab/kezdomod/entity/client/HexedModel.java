package net.dokilab.kezdomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dokilab.kezdomod.entity.animations.ModAnimationDefinitions;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.customs.HexedEntity;
import net.minecraft.world.entity.monster.Monster;

public class HexedModel<H extends Monster> extends HierarchicalModel<HexedEntity> {

	private final ModelPart hexed;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart hair;
	private final ModelPart torso;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart left_thi;
	private final ModelPart right_thi;

	public HexedModel(ModelPart root) {
		this.hexed = root.getChild("hexed");
		this.body = this.hexed.getChild("body");
		this.head = this.body.getChild("head");
		this.hair = this.head.getChild("hair");
		this.torso = this.body.getChild("torso"); // ✅ Make sure "torso" exists
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.left_leg = this.body.getChild("left_leg");
		this.right_leg = this.body.getChild("right_leg");
		this.left_thi = this.body.getChild("left_thi");
		this.right_thi = this.body.getChild("right_thi");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hexed = partdefinition.addOrReplaceChild("hexed", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = hexed.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 31).addBox(-5.0F, -3.0F, -6.0F, 10.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 3.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition body_r1 = torso.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(18, 16).addBox(-2.5F, -17.5F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, -2.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, -29.0F, 0.0F, 0.0F, 0.0F, -2.2253F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -29.0F, 0.0F, 0.0F, 0.0F, 2.2253F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(40, 0).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.1F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(40, 0).addBox(3.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.1F));

		PartDefinition left_thi = body.addOrReplaceChild("left_thi", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(1.0F, -25.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_thi = body.addOrReplaceChild("right_thi", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -25.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(HexedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		// Check real movement instead of limbSwingAmount
		boolean isMoving = entity.getDeltaMovement().horizontalDistanceSqr() > 0.0025;

		if (isMoving) {
			this.animateWalk(ModAnimationDefinitions.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			this.animate(entity.idleAnimationState, ModAnimationDefinitions.IDLE, ageInTicks, 1f);
		}
	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hexed.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return hexed;
	}
}
