package com.hirozee.lolmod.common.items;

import com.hirozee.lolmod.LolMod;
import com.hirozee.lolmod.common.entity.BoomerProjectileEntity;
import com.hirozee.lolmod.common.entity.ExplosiveChargeEntity;
import com.hirozee.lolmod.core.init.ItemInit;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.registries.ForgeRegistries;

public class BoomerItem extends Item {
  public BoomerItem(Item.Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
	  if (!playerIn.getCooldowns().isOnCooldown(this)) {
		  //ExplosiveChargeEntity explosiveCharge = new ExplosiveChargeEntity(worldIn, playerIn);
		  BoomerProjectileEntity boomerProjectile = new BoomerProjectileEntity(worldIn, (LivingEntity)playerIn, 0.0D, 0.0D, 0.0D);
		  ItemStack boomerAmmo = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("lolmod:boomer_ammo")));
		  //FireballEntity fireball = new FireballEntity(worldIn, (LivingEntity)playerIn, 0.0D, 0.0D, 0.0D);
		  /*if (playerIn.isCrouching()) {
			  explosiveCharge.setOwner((Entity)playerIn);
			  explosiveCharge.setPos(playerIn.getX(), playerIn.getY() + 1.5D, playerIn.getZ());
			  explosiveCharge.shootFromRotation((Entity)playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.0F, 0.0F);
			  worldIn.addFreshEntity((Entity)explosiveCharge);
			  playerIn.getCooldowns().addCooldown(this, 20);
		  } else {*/
			  boomerProjectile.setOwner((Entity)playerIn);
			  boomerProjectile.setItem(boomerAmmo);
			  boomerProjectile.setPos(playerIn.getX(), playerIn.getY() + 1.5D, playerIn.getZ());
			  boomerProjectile.shootFromRotation((Entity)playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 3.0F, 0.0F);
			  worldIn.addFreshEntity((Entity)boomerProjectile);
			  playerIn.getCooldowns().addCooldown(this, 20);
		  //} 
		  return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
	  } 
	  return InteractionResultHolder.fail(playerIn.getItemInHand(handIn));
  }
}
