package com.hirozee.lolmod.core.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {
  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "lolmod");
  
  public static final RegistryObject<SoundEvent> TRISTANA_AMBIENT_1 = createSound("tristana_ambient_1");
  
  public static final RegistryObject<SoundEvent> TRISTANA_AMBIENT_2 = createSound("tristana_ambient_2");
  
  public static final RegistryObject<SoundEvent> TRISTANA_AMBIENT_3 = createSound("tristana_ambient_3");
  
  public static final RegistryObject<SoundEvent> TRISTANA_AMBIENT_4 = createSound("tristana_ambient_4");
  
  private static RegistryObject<SoundEvent> createSound(String name) {
    ResourceLocation resourceLocation = new ResourceLocation("lolmod", name);
    return SOUNDS.register(name, () -> new SoundEvent(resourceLocation));
  }
}
