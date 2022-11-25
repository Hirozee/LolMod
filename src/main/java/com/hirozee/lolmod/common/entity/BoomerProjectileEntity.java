package com.hirozee.lolmod.common.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Explosion;

public class BoomerProjectileEntity extends Fireball {
  public int explosionPower = 1;
  
  public BoomerProjectileEntity(EntityType<? extends BoomerProjectileEntity> p_i50160_1_, Level p_i50160_2_) {
    super(p_i50160_1_, p_i50160_2_);
  }
  
  public BoomerProjectileEntity(Level p_i1771_1_, LivingEntity p_i1771_2_, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_) {
    super(EntityType.FIREBALL, p_i1771_2_, p_i1771_3_, p_i1771_5_, p_i1771_7_, p_i1771_1_);
  }
  
  public BoomerProjectileEntity(Level p_i1772_1_, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_) {
    super(EntityType.FIREBALL, p_i1772_2_, p_i1772_4_, p_i1772_6_, p_i1772_8_, p_i1772_10_, p_i1772_12_, p_i1772_1_);
  }
  
  public void tick() {
    if (this.tickCount == 5)
      remove(RemovalReason.DISCARDED); 
    super.tick();
  }
  
  protected void onHitEntity(EntityHitResult p_213868_1_) {
    super.onHitEntity(p_213868_1_);
    if (!this.level.isClientSide) {
      Entity entity = p_213868_1_.getEntity();
      Entity entity1 = getOwner();
      entity.hurt(DamageSource.fireball(this, entity1), 3.0F);
      doEnchantDamageEffects((LivingEntity)entity1, entity);
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
