package com.hirozee.lolmod.common.entity;

import com.hirozee.lolmod.core.init.EntityTypesInit;
import com.hirozee.lolmod.core.init.ItemInit;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ExplosiveChargeEntity extends Snowball {
	public ExplosiveChargeEntity(EntityType<ExplosiveChargeEntity> type, Level world) {
		// TODO Auto-generated constructor stub
		super(type, world);
	}
	
	public ExplosiveChargeEntity(LivingEntity entity, Level world) {
		super(EntityTypesInit.EXPLOSIVE_CHARGE.get(), world);
	}
	
	public ExplosiveChargeEntity(double x, double y, double z, Level world) {

		super(EntityTypesInit.EXPLOSIVE_CHARGE.get(), null);
	}

	protected Item getDefaultItem() {
		// TODO Auto-generated method stub
		return ItemInit.EXPLOSIVE_CHARGE.get().asItem();
	}

    /*protected IParticleData getParticle() {
        return Particle.DRAGON_BREATH;
    }*/
    
	
	/*protected void onImpact(HitResult result) {
	      remove(RemovalReason.DISCARDED); 
	}*/


	/*public ICustomPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}*/
}
/*	
	   private UUID ownerUUID;
	   private int ownerNetworkId;
	   private boolean leftOwner;

	   public ExplosiveChargeEntity(EntityType<? extends ExplosiveChargeEntity> type, World world) {
	      super(type, world);
	   }

	   public void setOwner(@Nullable Entity p_212361_1_) {
	      if (p_212361_1_ != null) {
	         this.ownerUUID = p_212361_1_.getUUID();
	         this.ownerNetworkId = p_212361_1_.getId();
	      }

	   }

	   @Nullable
	   public Entity getOwner() {
	      if (this.ownerUUID != null && this.level instanceof ServerWorld) {
	         return ((ServerWorld)this.level).getEntity(this.ownerUUID);
	      } else {
	         return this.ownerNetworkId != 0 ? this.level.getEntity(this.ownerNetworkId) : null;
	      }
	   }

	   protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
	      if (this.ownerUUID != null) {
	         p_213281_1_.putUUID("Owner", this.ownerUUID);
	      }

	      if (this.leftOwner) {
	         p_213281_1_.putBoolean("LeftOwner", true);
	      }

	   }

	   protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	      if (p_70037_1_.hasUUID("Owner")) {
	         this.ownerUUID = p_70037_1_.getUUID("Owner");
	      }

	      this.leftOwner = p_70037_1_.getBoolean("LeftOwner");
	   }

	   public void tick() {
	      if (!this.leftOwner) {
	         this.leftOwner = this.checkLeftOwner();
	      }

	      super.tick();
	   }

	   private boolean checkLeftOwner() {
	      Entity entity = this.getOwner();
	      if (entity != null) {
	         for(Entity entity1 : this.level.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (p_234613_0_) -> {
	            return !p_234613_0_.isSpectator() && p_234613_0_.isPickable();
	         })) {
	            if (entity1.getRootVehicle() == entity.getRootVehicle()) {
	               return false;
	            }
	         }
	      }

	      return true;
	   }

	   public void shoot(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
	      Vector3d vector3d = (new Vector3d(p_70186_1_, p_70186_3_, p_70186_5_)).normalize().add(this.random.nextGaussian() * (double)0.0075F * (double)p_70186_8_, this.random.nextGaussian() * (double)0.0075F * (double)p_70186_8_, this.random.nextGaussian() * (double)0.0075F * (double)p_70186_8_).scale((double)p_70186_7_);
	      this.setDeltaMovement(vector3d);
	      float f = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
	      this.yRot = (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI));
	      this.xRot = (float)(MathHelper.atan2(vector3d.y, (double)f) * (double)(180F / (float)Math.PI));
	      this.yRotO = this.yRot;
	      this.xRotO = this.xRot;
	   }

	   public void shootFromRotation(Entity p_234612_1_, float p_234612_2_, float p_234612_3_, float p_234612_4_, float p_234612_5_, float p_234612_6_) {
	      float f = -MathHelper.sin(p_234612_3_ * ((float)Math.PI / 180F)) * MathHelper.cos(p_234612_2_ * ((float)Math.PI / 180F));
	      float f1 = -MathHelper.sin((p_234612_2_ + p_234612_4_) * ((float)Math.PI / 180F));
	      float f2 = MathHelper.cos(p_234612_3_ * ((float)Math.PI / 180F)) * MathHelper.cos(p_234612_2_ * ((float)Math.PI / 180F));
	      this.shoot((double)f, (double)f1, (double)f2, p_234612_5_, p_234612_6_);
	      Vector3d vector3d = p_234612_1_.getDeltaMovement();
	      this.setDeltaMovement(this.getDeltaMovement().add(vector3d.x, p_234612_1_.isOnGround() ? 0.0D : vector3d.y, vector3d.z));
	   }

	   protected void onHit(RayTraceResult p_70227_1_) {
	      RayTraceResult.Type raytraceresult$type = p_70227_1_.getType();
	      if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
	         this.onHitEntity((EntityRayTraceResult)p_70227_1_);
	      } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
	         this.onHitBlock((BlockRayTraceResult)p_70227_1_);
	      }

	   }

	   protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
	   }

	   protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
	      BlockState blockstate = this.level.getBlockState(p_230299_1_.getBlockPos());
	     // blockstate.onProjectileHit(this.level, blockstate, p_230299_1_, this);
	   }

	   @OnlyIn(Dist.CLIENT)
	   public void lerpMotion(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
	      this.setDeltaMovement(p_70016_1_, p_70016_3_, p_70016_5_);
	      if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
	         float f = MathHelper.sqrt(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
	         this.xRot = (float)(MathHelper.atan2(p_70016_3_, (double)f) * (double)(180F / (float)Math.PI));
	         this.yRot = (float)(MathHelper.atan2(p_70016_1_, p_70016_5_) * (double)(180F / (float)Math.PI));
	         this.xRotO = this.xRot;
	         this.yRotO = this.yRot;
	         this.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
	      }

	   }

	   protected boolean canHitEntity(Entity p_230298_1_) {
	      if (!p_230298_1_.isSpectator() && p_230298_1_.isAlive() && p_230298_1_.isPickable()) {
	         Entity entity = this.getOwner();
	         return entity == null || this.leftOwner || !entity.isPassengerOfSameVehicle(p_230298_1_);
	      } else {
	         return false;
	      }
	   }

	   protected void updateRotation() {
	      Vector3d vector3d = this.getDeltaMovement();
	      float f = MathHelper.sqrt(getHorizontalDistanceSqr(vector3d));
	      this.xRot = lerpRotation(this.xRotO, (float)(MathHelper.atan2(vector3d.y, (double)f) * (double)(180F / (float)Math.PI)));
	      this.yRot = lerpRotation(this.yRotO, (float)(MathHelper.atan2(vector3d.x, vector3d.z) * (double)(180F / (float)Math.PI)));
	   }

	   protected static float lerpRotation(float p_234614_0_, float p_234614_1_) {
	      while(p_234614_1_ - p_234614_0_ < -180.0F) {
	         p_234614_0_ -= 360.0F;
	      }

	      while(p_234614_1_ - p_234614_0_ >= 180.0F) {
	         p_234614_0_ += 360.0F;
	      }

	      return MathHelper.lerp(0.2F, p_234614_0_, p_234614_1_);
	   }

	@Override
	protected void defineSynchedData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		// TODO Auto-generated method stub
		return null;
	}
}


*/
/*
public class ExplosiveCharge extends ProjectileItemEntity {
	  public int explosionPower = 1;

	  public ExplosiveCharge(EntityType<? extends ExplosiveCharge> p_i50159_1_, World p_i50159_2_) {
	    super(p_i50159_1_, p_i50159_2_);
	  }

	  public ExplosiveCharge(World p_i1774_1_, LivingEntity p_i1774_2_) {
	    super(EntityType.SNOWBALL, p_i1774_2_, p_i1774_1_);
	  }

	  public ExplosiveCharge(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
	    super(EntityType.SNOWBALL, p_i1775_2_, p_i1775_4_, p_i1775_6_, p_i1775_1_);
	  }

	  protected Item getDefaultItem() {
	    return Items.SNOWBALL;
	  }

	  public void tick() {
	    super.tick();
	    if (isPassenger()) {
	      setGlowing(true);
	      if (!isPassenger()) {
	        this.level.explode(null, getX(), getY(), getZ(), this.explosionPower, Explosion.Mode.NONE);
	        remove();
	      } else if (this.tickCount == 40) {
	        this.level.explode(null, getX(), getY(), getZ(), this.explosionPower, Explosion.Mode.NONE);
	        remove();
	      } 
	    } 
	  }

	  protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
	    super.onHitEntity(p_213868_1_);
	    if (!this.level.isClientSide) {
	      Entity entity = p_213868_1_.getEntity();
	      Entity explosiveCharge = getEntity();
	      explosiveCharge.startRiding(entity);
	    } 
	  }

	  public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
	    super.addAdditionalSaveData(p_213281_1_);
	    p_213281_1_.putInt("ExplosionPower", this.explosionPower);
	  }

	  public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	    super.readAdditionalSaveData(p_70037_1_);
	    if (p_70037_1_.contains("ExplosionPower", 99))
	      this.explosionPower = p_70037_1_.getInt("ExplosionPower"); 
	  }
	}
*/