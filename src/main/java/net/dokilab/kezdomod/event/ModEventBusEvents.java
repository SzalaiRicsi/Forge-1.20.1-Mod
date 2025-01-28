package net.dokilab.kezdomod.event;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.Modentities;
import net.dokilab.kezdomod.entity.customs.HexedEntity;
import net.dokilab.kezdomod.entity.customs.LurkerEntity;
import net.dokilab.kezdomod.entity.customs.MummyEntity;
import net.dokilab.kezdomod.entity.customs.SandstoneGolemEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KezdoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(Modentities.HEXED.get(), HexedEntity.createAttributes().build());

        event.put(Modentities.SANDSTONE_GOLEM.get(), SandstoneGolemEntity.createAttributes().build());

        event.put(Modentities.LURKER.get(), LurkerEntity.createAttributes().build());

        event.put(Modentities.MUMMY.get(), MummyEntity.createAttributes().build());
    }
}
