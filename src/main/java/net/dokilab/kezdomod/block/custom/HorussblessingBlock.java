package net.dokilab.kezdomod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.dokilab.kezdomod.sound.Modsounds;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HorussblessingBlock extends Block {
    private static final Random RANDOM = new Random();
    private static final Map<Player, Long> lastSoundTime = new HashMap<>();
    private static final long SOUND_COOLDOWN = 3000; // 4 seconds in milliseconds

    public HorussblessingBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) { // Server-side only
            ItemStack itemInHand = pPlayer.getItemInHand(pHand);
            Item coinItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation("kezdomod", "coin")); // Your coin item

            if (itemInHand.getItem() == coinItem) { // Check if holding a coin
                itemInHand.shrink(1); // Remove one coin

                // Spawn experience orb at block position
                if (pLevel instanceof ServerLevel serverLevel) {
                    ExperienceOrb orb = new ExperienceOrb(serverLevel, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5, 4);
                    serverLevel.addFreshEntity(orb);
                }

                // Play a random Horuss sound with cooldown
                playRandomHorussSound(pLevel, pPos, pPlayer);

                return InteractionResult.SUCCESS; // Interaction successful
            }
        }
        return InteractionResult.PASS; // Do nothing if not holding a coin
    }

    private void playRandomHorussSound(Level pLevel, BlockPos pPos, Player pPlayer) {
        long currentTime = System.currentTimeMillis();
        long lastTime = lastSoundTime.getOrDefault(pPlayer, 0L);

        if (currentTime - lastTime >= SOUND_COOLDOWN) { // Check if cooldown passed
            SoundEvent[] horussSounds = {
                    Modsounds.HORUSS_USE1.get(),
                    Modsounds.HORUSS_USE2.get(),
                    Modsounds.HORUSS_USE3.get()
            };

            SoundEvent randomSound = horussSounds[RANDOM.nextInt(horussSounds.length)]; // Pick a random sound
            pLevel.playSound(null, pPos, randomSound, SoundSource.BLOCKS, 1.0f, 1.0f);

            lastSoundTime.put(pPlayer, currentTime); // Update last sound time
        }
    }
}
