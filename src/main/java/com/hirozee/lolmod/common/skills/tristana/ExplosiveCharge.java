package com.hirozee.lolmod.common.items;

import java.util.Map;

import com.hirozee.lolmod.common.entity.ExplosiveChargeEntity;

import net.minecraft.server.WorldStem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;

public class ExplosiveChargeItem extends Item {
	
	public ExplosiveChargeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
			ItemStack stack = playerIn.getMainHandItem();
			if(!worldIn.isClientSide) {
				ExplosiveChargeEntity explosiveCharge = new ExplosiveChargeEntity(worldIn, playerIn);
				explosiveCharge.setItem(stack);
				explosiveCharge.shootFromRotation((Entity)playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.0F, 0.0F);
				explosiveCharge.setPos(playerIn.getX(), playerIn.getY() + 1.5D, playerIn.getZ());
				worldIn.addFreshEntity((Entity)explosiveCharge);	
			}
			return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
		}
}