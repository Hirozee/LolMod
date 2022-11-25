package com.hirozee.lolmod.common.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraft.core.dispenser.*;

public class CustomSpawnEggItem extends SpawnEggItem {
  protected static final List<CustomSpawnEggItem> EGGS_TO_ADD = new ArrayList<>();
  
  protected static final DefaultDispenseItemBehavior behavior = new DefaultDispenseItemBehavior();
  
  private final Lazy<? extends EntityType<?>> lazyEntity;
  
  @SuppressWarnings("deprecation")
public CustomSpawnEggItem(RegistryObject<? extends EntityType<?>> entity, int primaryColor, int secondaryColor, Item.Properties properties) {
    super(null, primaryColor, secondaryColor, properties);
    this.lazyEntity = Lazy.of(entity::get);
    EGGS_TO_ADD.add(this);
  }
  
  public static void initSpawnEggs() {
    @SuppressWarnings("unchecked")
	Map<EntityType<?>, SpawnEggItem> EGGS = (Map<EntityType<?>, SpawnEggItem>)ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "");
    for (SpawnEggItem item : EGGS_TO_ADD) {
      EGGS.put(item.getType(null), item);
    } 
    EGGS_TO_ADD.clear();
  }
  
  public EntityType<?> getType(CompoundTag p_208076_1_) {
    return (EntityType<?>)this.lazyEntity.get();
  }
}
