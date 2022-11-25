package com.hirozee.lolmod.util;

import com.hirozee.lolmod.client.render.entity.model.TristanaModel;
import com.hirozee.lolmod.client.render.entity.renderer.TristanaRender;
import com.hirozee.lolmod.core.init.EntityTypesInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
@EventBusSubscriber(modid = "lolmod", bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		
		
		//RenderingRegistry.registerEntityRenderingHandler((EntityType<TristanaEntity>)EntityTypesInit.TRISTANA.get(), com.hirozee.lolmod.client.render.entity.renderer.TristanaRender::new);

		//RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.EXPLOSIVE_CHARGE.get(), ExplosiveChargeRender::new);
		
			//ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
			//RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.EXPLOSIVE_CHARGE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
		
		//RenderingRegistry.registerEntityRenderingHandler((EntityType<ExplosiveChargeEntity>)EntityTypesInit.EXPLOSIVE_CHARGE.get(), new ExplosiveChargeRender());
	}
	
	public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EntityTypesInit.TRISTANA.get(), TristanaRender::new);
	}

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TristanaModel.LAYER_LOCATION, TristanaModel::createBodyLayer);
	}

	/*@SuppressWarnings("unused")
	private void registerEntityModels(Supplier<Minecraft> minecraft) {
		ItemRenderer renderer = minecraft.get().getItemRenderer();
		RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.EXPLOSIVE_CHARGE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
	}*/

}
/*
  private static class ExplosiveChargeRender implements IRenderFactory<ExplosiveChargeEntity> {
	  @Override
	  public EntityRenderer<? super ExplosiveChargeEntity> createRenderFor(EntityRendererManager manager) {
		  ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		  return new SpriteRenderer<>(manager, itemRenderer);
	  }
  }*/
