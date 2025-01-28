package net.dokilab.kezdomod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dokilab.kezdomod.entity.animations.ModAnimationDefinitions;
import net.dokilab.kezdomod.entity.customs.LurkerEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class LurkerModel<T extends LurkerEntity> extends HierarchicalModel<T> {

	private final ModelPart lurker;
	private final ModelPart head;
	private final ModelPart headwear;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public LurkerModel(ModelPart root) {
		this.lurker = root.getChild("lurker");
		this.head = this.lurker.getChild("head");
		this.headwear = this.head.getChild("headwear");
		this.body = this.lurker.getChild("body");
		this.left_arm = this.lurker.getChild("left_arm");
		this.right_arm = this.lurker.getChild("right_arm");
		this.left_leg = this.lurker.getChild("left_leg");
		this.right_leg = this.lurker.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition lurker = partdefinition.addOrReplaceChild("lurker", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = lurker.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = lurker.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(49, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition left_arm = lurker.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition right_arm = lurker.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition left_leg = lurker.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, -12.0F, 0.1F));

		PartDefinition right_leg = lurker.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.1F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(LurkerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		// Handle head rotation
		this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
		this.head.xRot = headPitch * Mth.DEG_TO_RAD;

		// Handle animations based on the Lurker's state
		if (entity.isHidden()) {
			// Play hidden animation
			this.animate(entity.hiddenAnimationState, ModAnimationDefinitions.LURKER_HIDE, ageInTicks, 1f);
		} else if (entity.hasScaredPlayer()) {
			// Play scare animation once
			this.animate(entity.idleAnimationState, ModAnimationDefinitions.LURKER_SCARE, ageInTicks, 1f);
		} else if (entity.getDeltaMovement().horizontalDistanceSqr() > 0.0025) {
			// Play walking animation
			this.animateWalk(ModAnimationDefinitions.LURKER_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			// Play idle animation
			this.animate(entity.idleAnimationState, ModAnimationDefinitions.LURKER_IDLE, ageInTicks, 1f);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		lurker.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return lurker;
	}
}