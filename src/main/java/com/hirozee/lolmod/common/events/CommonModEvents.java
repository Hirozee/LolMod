package com.hirozee.lolmod.common.events;

import com.hirozee.lolmod.common.entity.TristanaEntity;
import com.hirozee.lolmod.core.init.EntityTypesInit;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
@EventBusSubscriber(modid = "lolmod", bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
	
	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {}
		

	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityTypesInit.TRISTANA.get(), TristanaEntity.createAttributes().build());
	}
	 
}
	