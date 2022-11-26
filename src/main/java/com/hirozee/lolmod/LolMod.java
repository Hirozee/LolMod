package com.hirozee.lolmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hirozee.lolmod.common.entity.TristanaEntity;
import com.hirozee.lolmod.common.items.CustomSpawnEggItem;
import com.hirozee.lolmod.core.init.EntityTypesInit;
import com.hirozee.lolmod.core.init.ItemInit;
import com.hirozee.lolmod.core.init.SoundInit;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent.RegisterHelper;


@Mod("lolmod")
public class LolMod {
	  /*public static ExplosiveChargeItem ExplosiveChargeItem;
	  public static EntityType<ExplosiveCharge> ExplosiveCharge;*/
	  
  public static final Logger LOGGER = LogManager.getLogger();
  
/*
  @SubscribeEvent
  public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
    MinecraftForge.EVENT_BUS.register(ServerLifecycleEvent.class);  // used for our test harness code only; delete if you don't want that
    MinecraftForge.EVENT_BUS.register(RegisterCommandsEvent.class);  // used for our test harness code only; delete if you don't want that
  }*/
  
  public static final String MOD_ID = "lolmod";
  

  public LolMod() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    //bus.addListener(this::setup);
    SoundInit.SOUNDS.register(bus);
    EntityTypesInit.ENTITY_TYPES.register(bus);
    ItemInit.ITEMS.register(bus);
    MinecraftForge.EVENT_BUS.register(this);
    
  } 
  
  /*@SuppressWarnings("unused")
  private void registerEntityModels(Supplier<Minecraft> minecraft) {
	  ItemRenderer renderer = minecraft.get().getItemRenderer();
	  RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.EXPLOSIVE_CHARGE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
  }*/
  /*
  @SubscribeEvent
  public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
	  ExplosiveChargeItem = new ExplosiveChargeItem(null);
	  ExplosiveChargeItem.setRegistryName("mbe81a_emoji_happy_registry_name");
    itemRegisterEvent.getRegistry().register(ExplosiveChargeItem);
    }
  
  @SubscribeEvent
  public static void onEntityTypeRegistration(RegistryEvent.Register<EntityType<?>> entityTypeRegisterEvent) {
	  ExplosiveCharge = EntityType.Builder.<ExplosiveCharge>of(ExplosiveCharge::new, EntityClassification.MISC)
            .sized(0.25F, 0.25F)
            .build("minecraftbyexample:mbe81a_emoji_type_registry_name");
	  ExplosiveCharge.setRegistryName("minecraftbyexample:mbe81a_emoji_type_registry_name");
    entityTypeRegisterEvent.getRegistry().register(ExplosiveCharge);
  }*/

  @SubscribeEvent
  public static void onRegisterEntities(RegisterHelper<EntityType<?>> event) {
	   CustomSpawnEggItem.initSpawnEggs();
  }

/*private void setup(FMLCommonSetupEvent event) {
	  DeferredWorkQueue.lookup(() -> GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>)EntityTypesInit.TRISTANA.get(), TristanaEntity.setAttributes().build()));
	  
  }*/

  public static class LolModGroup extends CreativeModeTab {
	  public LolModGroup(String label) {
		  super("LolMod");
	  }

	  public ItemStack makeIcon() {
		  return ((CustomSpawnEggItem)ItemInit.TRISTANA_SPAWN_EGG.get()).getDefaultInstance();
	  }
  }

  public static final CreativeModeTab LOLMOD_TAB = new CreativeModeTab (MOD_ID) {
	  @Override
	  public ItemStack makeIcon() {
		  return ItemInit.TRISTANA_SPAWN_EGG.get().getDefaultInstance();
	  }
  };
}

