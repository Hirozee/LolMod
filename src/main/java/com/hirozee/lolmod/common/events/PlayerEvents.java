package com.hirozee.lolmod.common.events;

import com.hirozee.lolmod.core.init.EntityTypesInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/*@EventBusSubscriber(modid = "yordlemod")
// I have to fix this
public class PlayerEvents {
  @SubscribeEvent
  public static void onBiomeLoad(BiomeModifier event) {
    if (event.getName() == null)
      return; 
    MobSpawnSettingsBuilder spawns = event.getSpawns();
    if (event.getCategory().equals(Biomes.FOREST))
      spawns.addSpawn(MobCategory.CREATURE, new SpawnerData((EntityType<?>)EntityTypesInit.TRISTANA.get(), 18, 1, 1)); 
  }
}*/
