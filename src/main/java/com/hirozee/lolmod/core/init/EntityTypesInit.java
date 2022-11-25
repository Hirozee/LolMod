package com.hirozee.lolmod.core.init;

import com.hirozee.lolmod.common.entity.ExplosiveChargeEntity;
import com.hirozee.lolmod.common.entity.TristanaEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypesInit {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, 
      "yordlemod");
  public static void init()
  {
	  ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
  }
  public static final RegistryObject<EntityType<TristanaEntity>> TRISTANA = ENTITY_TYPES.register("tristana",
		  () -> EntityType.Builder.of(TristanaEntity::new, MobCategory.CREATURE).sized(0.6F, 1.5F).build((new ResourceLocation("lolmod", "tristana")).toString()));
  
  //public static final RegistryObject<EntityType<BoomerProjectileEntity>> BOOMER_PROJECTILE = ENTITY_TYPES.register("boomer_projectile", () -> EntityType.Builder.of(BoomerProjectile::new, EntityClassification.MISC).build("boomer_projectile"));
  //public static final RegistryObject<EntityType<ExplosiveChargeEntity>> EXPLOSIVE_CHARGE = ENTITY_TYPES.register("explosive_charge",
	//	  () -> EntityType.Builder.<ExplosiveChargeEntity>of(ExplosiveChargeEntity::new, EntityClassification.MISC).sized(1.0F, 1.0F).build((new ResourceLocation("yordlemod", "explosive_charge")).toString()));

  public static final RegistryObject<EntityType<ExplosiveChargeEntity>> EXPLOSIVE_CHARGE = ENTITY_TYPES.register("explosive_charge",
		  () -> EntityType.Builder.<ExplosiveChargeEntity>of(ExplosiveChargeEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).build((new ResourceLocation("lolmod", "explosive_charge")).toString()));

  
 /*public static EntityType<ExplosiveChargeEntity> EXPLOSIVE_CHARGE;
  public static void onEntityTypeRegistration(RegistryEvent.Register<EntityType<?>> entityTypeRegisterEvent) {
	  EXPLOSIVE_CHARGE = EntityType.Builder.<ExplosiveChargeEntity>of(ExplosiveChargeEntity::new, EntityClassification.MISC)
			  .sized(0.25F, 0.25F)
			  .build("yordlemod:explosive_charge");
	  EXPLOSIVE_CHARGE.setRegistryName("yordlemod:explosive_charge");
	  entityTypeRegisterEvent.getRegistry().register(EXPLOSIVE_CHARGE);
  }*/
  /*public static final RegistryObject<EntityType<ExplosiveCharge>> EXPLOSIVE_CHARGE = ENTITY_TYPES.register("explosive_charge",
		  () -> EntityType.Builder.<ExplosiveCharge>of(ExplosiveCharge::new, EntityClassification.MISC).sized(1, 1).build((new ResourceLocation("yordlemod", "explosive_charge")).toString()));*/
}
