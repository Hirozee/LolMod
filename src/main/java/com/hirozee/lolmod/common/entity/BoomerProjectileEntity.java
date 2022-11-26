package com.hirozee.lolmod.common.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.OptionalInt;

import javax.annotation.Nullable;

import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.level.Explosion;

public class BoomerProjectileEntity extends SmallFireball {
  public int explosionPower = 1;

  public BoomerProjectileEntity(EntityType<? extends BoomerProjectileEntity> p_i50160_1_, Level p_i50160_2_) {
	    super(p_i50160_1_, p_i50160_2_);
	  }
	  
	  public BoomerProjectileEntity(Level p_i1771_1_, LivingEntity p_i1771_2_, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_) {
	    super(EntityType.SMALL_FIREBALL, p_i1771_1_);
	  }
	  
	  public BoomerProjectileEntity(Level p_i1772_1_, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_) {
	    super(EntityType.SMALL_FIREBALL, p_i1772_1_);
	  }
	  
	  public void tick() {
	    if (this.tickCount == 8)
	      this.discard(); 
	    super.tick();
	  }


  
@SuppressWarnings("unused")
private ParticleOptions getParticle() {
     ItemStack itemstack = this.getItem();
     return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.CAMPFIRE_COSY_SMOKE : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
  }
   

  protected void onHit(HitResult p_37406_) {
     super.onHit(p_37406_);
     if (!this.level.isClientSide) {
        this.level.broadcastEntityEvent(this, (byte)3);
        this.discard();
     }
  }
  
  @Override
	protected void onHitBlock(BlockHitResult p_37384_) {
		// TODO Auto-generated method stub
		super.onHitBlock(p_37384_);
	     if (!this.level.isClientSide) {
	         this.level.broadcastEntityEvent(this, (byte)3);
	         this.discard();
	      }
	}
  protected void onHitEntity(EntityHitResult p_36757_) {
    super.onHitEntity(p_36757_);
    if (!this.level.isClientSide) {
      Entity entity = p_36757_.getEntity();
      Entity owner = getOwner();
      entity.hurt(DamageSource.fireball(this, owner), 3.0F);
      doEnchantDamageEffects((LivingEntity)owner, entity);
      if (((LivingEntity)entity).getHealth() <= 1.5F)
        this.level.explode(null, getX(), getY(), getZ(), this.explosionPower, Explosion.BlockInteraction.NONE); 
    } 
  }

  public void addAdditionalSaveData(CompoundTag p_213281_1_) {
	  this.addAdditionalSaveData(p_213281_1_);
    p_213281_1_.putInt("ExplosionPower", this.explosionPower);
  }
  
  public void readAdditionalSaveData(CompoundTag p_70037_1_) {
    this.readAdditionalSaveData(p_70037_1_);
    if (p_70037_1_.contains("ExplosionPower", 99))
      this.explosionPower = p_70037_1_.getInt("ExplosionPower"); 
  }


}
