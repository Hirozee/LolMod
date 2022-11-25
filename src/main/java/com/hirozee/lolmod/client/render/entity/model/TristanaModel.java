package com.hirozee.lolmod.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;

public class TristanaModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("lolmod", "tristana"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart armL;
	private final ModelPart armR;
	private final ModelPart legL;
	private final ModelPart legR;
	private final ModelPart arms;
	private final ModelPart legs;

	public TristanaModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.armL = root.getChild("armL");
		this.armR = root.getChild("armR");
		this.legL = root.getChild("legL");
		this.legR = root.getChild("legR");
		this.arms = root.getChild("arms");
		this.legs = root.getChild("legs");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armL = arms.addOrReplaceChild("armL", CubeListBuilder.create().texOffs(26, 16).addBox(0.0F, 0.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition armR = arms.addOrReplaceChild("armR", CubeListBuilder.create().texOffs(16, 16).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition legL = legs.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(54, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition legR = legs.addOrReplaceChild("legR", CubeListBuilder.create().texOffs(44, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
				.texOffs(0, 26).addBox(-4.0F, -9.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(12, 26).addBox(1.0F, -9.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(3.5F, -4.0F, 0.0F));

		PartDefinition earL = ears.addOrReplaceChild("earL", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earL_r1 = earL.addOrReplaceChild("earL_r1", CubeListBuilder.create().texOffs(50, 26).addBox(-0.25F, -2.0F, -1.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, -0.1745F));

		PartDefinition earR = ears.addOrReplaceChild("earR", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earR_r1 = earR.addOrReplaceChild("earR_r1", CubeListBuilder.create().texOffs(36, 26).addBox(-6.0F, -2.0F, 0.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.75F, 0.0F, -1.0F, 0.0F, 0.2618F, 0.1745F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}


	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * 0.017453292F;
		this.head.yRot = netHeadYaw * 0.017453292F;
		this.armL.xRot = limbSwing * 1.3324F;
		this.armR.xRot = limbSwing * 1.3324F;
		this.legL.xRot = limbSwing * 1.3324F;
		this.legR.xRot = limbSwing * 1.3324F;
	}


	public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		if (((TamableAnimal)p_212843_1_).isInSittingPose()) {
			this.head.setPos(0.0F, 15.0F, 0.0F);
			this.body.setPos(0.0F, 15.0F, 0.0F);
			this.arms.xRot = -0.2F;
			this.legs.xRot = -1.6F;
			this.legL.zRot = -0.4F;
			this.legR.zRot = 0.4F;
		} else {
			this.body.setPos(0.0F, 8.0F, 0.0F);
			this.head.setPos(0.0F, 8.0F, 0.0F);
			this.arms.xRot = 0.0F;
			this.legs.xRot = 0.0F;
			this.legL.zRot = 0.0F;
			this.legR.zRot = 0.0F;
		} 
	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}	
}
