package net.dokilab.kezdomod.entity.customs;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class LurkerEntity extends PathfinderMob {
    private static final EntityDataAccessor<Boolean> HIDDEN = SynchedEntityData.defineId(LurkerEntity.class, EntityDataSerializers.BOOLEAN);
    public AnimationState idleAnimationState = new AnimationState(); // Properly initialize
    public AnimationState hiddenAnimationState = new AnimationState(); // Properly initialize
    private boolean hasScaredPlayer = false;

    public LurkerEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
        this.entityData.set(HIDDEN, true); // Starts hidden
        this.setInvisible(true); // Make it invisible
        this.setNoAi(true); // Disable AI
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDDEN, true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LurkerScareGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false)); // Add attack goal
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0)); // Add movement goal
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true)); // Target players
    }

    public boolean isHidden() {
        return this.entityData.get(HIDDEN);
    }

    public void triggerLurkerScare() {
        if (!this.level().isClientSide && !hasScaredPlayer) {
            this.entityData.set(HIDDEN, false); // Become visible
            this.setInvisible(false); // Make it visible
            this.setNoAi(false); // Enable AI
            this.playScareSound();
            this.playLurkerScareAnimation(); // Play animation
            hasScaredPlayer = true;
        }
    }

    private void playScareSound() {
        SoundEvent scareSound = SoundEvents.WARDEN_ROAR; // Replace with actual LURKER_SCARE sound
        this.playSound(scareSound, 1.0F, 1.0F);
    }

    private void playLurkerScareAnimation() {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.broadcastEntityEvent(this, (byte) 60); // Custom animation trigger
            hasScaredPlayer = true;
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 60) {
            // Play custom animation here if needed
        } else {
            super.handleEntityEvent(id);
        }
    }

    public boolean hasScaredPlayer() {
        return false;
    }

    static class LurkerScareGoal extends Goal {
        private final LurkerEntity lurker;
        private Player targetPlayer;

        public LurkerScareGoal(LurkerEntity lurker) {
            this.lurker = lurker;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (lurker.hasScaredPlayer) return false;
            targetPlayer = lurker.level().getNearestPlayer(lurker, 4); // 4 blocks range
            return targetPlayer != null && lurker.distanceToSqr(targetPlayer) <= 16; // 4^2 = 16
        }

        @Override
        public void start() {
            lurker.triggerLurkerScare();
        }

        @Override
        public void tick() {
            if (targetPlayer != null && lurker.hasScaredPlayer) {
                lurker.getNavigation().moveTo(targetPlayer, 1.0);
            }
        }
    }
}