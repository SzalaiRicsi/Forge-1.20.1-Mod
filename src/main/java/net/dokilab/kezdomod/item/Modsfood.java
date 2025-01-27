package net.dokilab.kezdomod.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Modsfood {
    public static final FoodProperties FRESH_DATE = new FoodProperties.Builder().nutrition(2)
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30), 0.1f).fast().build();

    public static final FoodProperties DATE = new FoodProperties.Builder().nutrition(4)
            .saturationMod(0.4f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 50), 0.5f).fast().alwaysEat().build();
}
