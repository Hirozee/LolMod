package com.hirozee.lolmod.client.render.entity.renderer;

import com.hirozee.lolmod.client.render.entity.model.TristanaModel;
import com.hirozee.lolmod.common.entity.TristanaEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TristanaRender <T extends TristanaEntity> extends MobRenderer<T, TristanaModel<T>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation("lolmod", "textures/entity/tristana.png");

	public TristanaRender(EntityRendererProvider.Context ctx) {
		super (ctx, new TristanaModel(ctx.bakeLayer(TristanaModel.LAYER_LOCATION)), 0.5f);
	}
	
	public ResourceLocation getTextureLocation(TristanaEntity entity) {
		return TEXTURE;
	}
}
