package net.dokilab.kezdomod.sound;

import net.dokilab.kezdomod.KezdoMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Modsounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KezdoMod.MOD_ID);


    public static final RegistryObject<SoundEvent> COIN_GOLD_SACK_BREAK = registerSoundEvent("coin_gold_sack_break");
    public static final RegistryObject<SoundEvent> COIN_GOLD_SACK_STEP = registerSoundEvent("coin_gold_sack_step");
    public static final RegistryObject<SoundEvent> COIN_GOLD_SACK_FALL = registerSoundEvent("coin_gold_sack_fall");
    public static final RegistryObject<SoundEvent> COIN_GOLD_SACK_PLACE = registerSoundEvent("coin_gold_sack_place");
    public static final RegistryObject<SoundEvent> COIN_GOLD_SACK_HIT = registerSoundEvent("coin_gold_sack_hit");

    public static final ForgeSoundType coin_gold_sack_sounds = new ForgeSoundType(1f, 1f, Modsounds.COIN_GOLD_SACK_BREAK, Modsounds.COIN_GOLD_SACK_STEP,
            Modsounds.COIN_GOLD_SACK_PLACE, Modsounds.COIN_GOLD_SACK_HIT, Modsounds.COIN_GOLD_SACK_FALL);

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(KezdoMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);
    }
}
