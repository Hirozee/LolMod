package com.hirozee.lolmod.common.entity;

import com.hirozee.lolmod.core.init.EntityTypesInit;
import com.hirozee.lolmod.core.init.ItemInit;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.living.MobEffectEvent.Remove;

public class ExplosiveChargeEntity extends Snowball {
	public int explosionPower = 2;
	   public ExplosiveChargeEntity(EntityType<? extends Snowball> playerIn, Level worldIn) {
	      super(playerIn, worldIn);
	   }

	   public ExplosiveChargeEntity(Level worldIn, LivingEntity playerIn) {
	      super(EntityType.SNOWBALL, worldIn);
	   }

	   public ExplosiveChargeEntity(Level worldIn, double p_37395_, double p_37396_, double p_37397_) {
	      super(EntityType.SNOWBALL, worldIn);
	   }


	protected Item getDefaultItem() {
	      return Items.SNOWBALL;
	   }

	   private ParticleOptions getParticle() {
	      ItemStack itemstack = this.getItemRaw();
	      return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
	   }

	   public void handleEntityEvent(byte p_37402_) {
	      if (p_37402_ == 3) {
	         ParticleOptions particleoptions = this.getParticle();

	         for(int i = 0; i < 8; ++i) {
	            this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
	         }
	      }

	   }

	   protected void onHitEntity(EntityHitResult p_213868_1_) {
	     super.onHitEntity(p_213868_1_);
	     if (!this.level.isClientSide) {
	       Entity entity = p_213868_1_.getEntity();
	       Entity owner = getOwner();
	       doEnchantDamageEffects((LivingEntity)owner, entity);
	       this.startRiding(entity);
	       this.setGlowingTag(true);
	       if (this.tickCount == 10)
	         this.level.explode(null, getX(), getY(), getZ(), this.explosionPower, Explosion.BlockInteraction.NONE); 
	     } 
	   }
	   /*protected void onHitEntity(EntityHitResult p_37404_) {
	      super.onHitEntity(p_37404_);
	      Entity entity = p_37404_.getEntity();
	      entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
	   }*/

	   protected void onHit(HitResult p_37406_) {
	      super.onHit(p_37406_);
	      if (!this.level.isClientSide) {
	         this.level.broadcastEntityEvent(this, (byte)3);
	         this.discard();
	      }
	   }
}
