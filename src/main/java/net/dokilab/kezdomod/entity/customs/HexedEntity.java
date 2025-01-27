package net.dokilab.kezdomod.entity.customs;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.sound.Modsounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HexedEntity extends Monster {

    public HexedEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        // Check if the mob is in a standing pose (not crouching)
        if (this.getPose() == Pose.STANDING) {
            // Map the movement speed to the walk animation speed
            float movementSpeed = (float) this.getAttribute(Attributes.MOVEMENT_SPEED).getValue();  // Get current speed

            // Adjust the walk animation speed based on movement speed
            float walkSpeedFactor = Math.min(movementSpeed * 10.0F, 1.0F); // Adjust factor to match the speed

            // Update the walk animation state
            this.walkAnimation.update(walkSpeedFactor, 0.2F);  // First parameter is animation speed
        } else {
            // Set animation to zero if the mob is not standing
            this.walkAnimation.update(0.0F, 0.0F);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        // Melee attack goal: Moves and attacks players and villagers
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));

        // Random movement
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.10));

        // Look at players
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f));

        // **Target Selector (Hostility)**:
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)); // Retaliate if attacked
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Villager.class, true));
    }

    // Define the entity's attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)  // Health
                .add(Attributes.MOVEMENT_SPEED, 0.22)  // Movement Speed
                .add(Attributes.ATTACK_DAMAGE, 4.0)  // Attack Damage
                .add(Attributes.ARMOR, 10.0)  // Armor
                .add(Attributes.FOLLOW_RANGE, 16.0) // Detection Range
                .add(Attributes.ATTACK_SPEED, 1.2); // Attacks every second
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return Modsounds.HEXED_ATTACK.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return Modsounds.HEXED_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(Modsounds.HEXED_STEP1.get(), 0.8F, 1.0F);
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(KezdoMod.MOD_ID,"loot_tables/entities/hexed.json");
    }
}
