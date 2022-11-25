package com.hirozee.lolmod.common.entity;

import com.hirozee.lolmod.core.init.SoundInit;
import java.util.Random;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.event.ForgeEventFactory;
import  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;

public class TristanaEntity extends TamableAnimal {
  public TristanaEntity(EntityType<? extends TamableAnimal> type, Level worldIn) {
    super(type, worldIn);
    setTame(false);
  }
  
  public static Builder setAttributes() {
    return createMobAttributes()
      .add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 4.0D);
  }
  
  protected void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
    this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
    this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
    this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
    this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
    this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
  }
  
  protected SoundEvent getAmbientSound() {
    int random = (new Random()).ints(0, 5).findFirst().getAsInt();
    switch (random) {
      case 1:
        random = 1;
        return (SoundEvent)SoundInit.TRISTANA_AMBIENT_1.get();
      case 2:
        random = 2;
        return (SoundEvent)SoundInit.TRISTANA_AMBIENT_2.get();
      case 3:
        random = 3;
        return (SoundEvent)SoundInit.TRISTANA_AMBIENT_3.get();
      case 4:
        random = 4;
        return (SoundEvent)SoundInit.TRISTANA_AMBIENT_4.get();
    } 
    return (SoundEvent)SoundInit.TRISTANA_AMBIENT_1.get();
  }
  
  protected SoundEvent getDeathSound() {
    return SoundEvents.BAT_DEATH;
  }
  
  protected SoundEvent getHurtSound(EntityDamageSource source) {
    return SoundEvents.BAT_DEATH;
  }
  
  public void aiStep() {
    super.aiStep();
    if (!this.level.isClientSide && !isPathFinding() && this.onGround)
      this.level.broadcastEntityEvent((Entity)this, (byte)8); 
  }
  
  
  public boolean hurt(EntityDamageSource p_70097_1_, float p_70097_2_) {
    if (isInvulnerableTo(p_70097_1_))
      return false; 
    Entity entity = p_70097_1_.getEntity();
    setOrderedToSit(false);
    if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow))
      p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F; 
    return super.hurt(p_70097_1_, p_70097_2_);
  }
  
  public boolean doHurtTarget(Entity p_70652_1_) {
    boolean flag = p_70652_1_.hurt(EntityDamageSource.mobAttack((LivingEntity)this), (int)getAttributeValue(Attributes.ATTACK_DAMAGE));
    if (flag)
      doEnchantDamageEffects((LivingEntity)this, p_70652_1_); 
    return flag;
  }
  
  public void setTame(boolean p_70903_1_) {
    super.setTame(p_70903_1_);
    if (p_70903_1_) {
      getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
      setHealth(20.0F);
    } else {
      getAttribute(Attributes.MAX_HEALTH).setBaseValue(10.0D);
    } 
    getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0D);
  }
  
  @SuppressWarnings("rawtypes")
public InteractionResult mobInteract(Player playerIn, InteractionHand handIn) {
	  ItemStack itemstack = playerIn.getItemInHand(handIn);
    Item item = itemstack.getItem();
    if (this.level.isClientSide) {
      boolean flag = !(!isOwnedBy((LivingEntity)playerIn) && !isTame() && (item != Items.BONE || isTame()));
      return flag ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
    } 
    if (isTame()) {
      if (isFood(itemstack) && getHealth() < getMaxHealth()) {
        if (!playerIn.getAbilities().instabuild)
          itemstack.shrink(1); 
        heal(item.getFoodProperties().getNutrition());
        return InteractionResult.SUCCESS;
      } 
      if (isOrderedToSit()) {
        setOrderedToSit(false);
      } else if (!isOrderedToSit()) {
        setOrderedToSit(true);
      } 
    } else if (item == Items.BONE) {
      if (!playerIn.getAbilities().instabuild)
        itemstack.shrink(1); 
      if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame((Animal)this, playerIn)) {
        tame(playerIn);
        this.navigation.stop();
        setTarget(null);
        setOrderedToSit(true);
        this.level.broadcastEntityEvent((Entity)this, (byte)7);
      } else {
        this.level.broadcastEntityEvent((Entity)this, (byte)6);
      } 
      return InteractionResult.SUCCESS;
    } 
    return super.mobInteract(playerIn, handIn);
  }
  
  public boolean canMate(Animal p_70878_1_) {
    if (p_70878_1_ == this)
      return false; 
    if (!isTame())
      return false; 
    if (!(p_70878_1_ instanceof TristanaEntity))
      return false; 
    TristanaEntity tristanaentity = (TristanaEntity)p_70878_1_;
    if (!tristanaentity.isTame())
      return false; 
    if (tristanaentity.isInSittingPose())
      return false; 
    return (isInLove() && tristanaentity.isInLove());
  }
  
  
  public boolean wantsToAttack(LivingEntity entity, LivingEntity owner) {
    if (!(entity instanceof Creeper) && !(entity instanceof Ghast)) {
      if (entity instanceof TristanaEntity) {
        TristanaEntity tristanaentity = (TristanaEntity)entity;
        return !(tristanaentity.isTame() && tristanaentity.getOwner() == owner);
      } 
      if (entity instanceof Player && owner instanceof Player && !((Player)owner).canHarmPlayer((Player)entity))
        return false; 
      if (entity instanceof AbstractHorse && ((AbstractHorse)entity).isTamed())
        return false; 
      return !(entity instanceof TamableAnimal && ((TamableAnimal)entity).isTame());
    } 
    return false;
  }

@Override
public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
	// TODO Auto-generated method stub
	return null;
}
}
