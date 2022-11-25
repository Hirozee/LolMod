package com.hirozee.lolmod.core.init;

import com.hirozee.lolmod.LolMod;
import com.hirozee.lolmod.common.items.BlueEssenceItem;
import com.hirozee.lolmod.common.items.BoomerAmmo;
import com.hirozee.lolmod.common.items.BoomerItem;
import com.hirozee.lolmod.common.items.BusterShotItem;
import com.hirozee.lolmod.common.items.CustomSpawnEggItem;
import com.hirozee.lolmod.common.items.ExplosiveChargeItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "lolmod");
  
  public static final RegistryObject<CustomSpawnEggItem> TRISTANA_SPAWN_EGG = ITEMS.register("tristana_spawn_egg", 
		  () -> new CustomSpawnEggItem(EntityTypesInit.TRISTANA, 13155071, 15263976, (new Item.Properties()).tab(LolMod.LOLMOD_TAB)));
  
  public static final RegistryObject<BoomerItem> BOOMER = ITEMS.register("boomer", 
		  () -> new BoomerItem((new Item.Properties()).stacksTo(1).tab(LolMod.LOLMOD_TAB).rarity(Rarity.EPIC)));
  
  public static final RegistryObject<BlueEssenceItem> BLUE_ESSENCE = ITEMS.register("blue_essence", 
		  () -> new BlueEssenceItem((new Item.Properties()).stacksTo(64).tab(LolMod.LOLMOD_TAB).rarity(Rarity.RARE)));
  
  public static final RegistryObject<BusterShotItem> BUSTER_SHOT_ITEM = ITEMS.register("buster_shot_item", 
		  () -> new BusterShotItem((new Item.Properties()).stacksTo(16).tab(LolMod.LOLMOD_TAB).rarity(Rarity.COMMON)));
  
  public static final RegistryObject<BoomerAmmo> BOOMER_AMMO = ITEMS.register("boomer_ammo", 
		  () -> new BoomerAmmo((new Item.Properties()).stacksTo(16).tab(LolMod.LOLMOD_TAB).rarity(Rarity.COMMON)));
  
  public static final RegistryObject<ExplosiveChargeItem> EXPLOSIVE_CHARGE = ITEMS.register("explosive_charge_item", 
		  () -> new ExplosiveChargeItem((new Item.Properties()).stacksTo(1).tab(LolMod.LOLMOD_TAB).rarity(Rarity.COMMON)));

}
