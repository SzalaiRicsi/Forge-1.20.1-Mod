package net.dokilab.kezdomod.event;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.client.HexedModel;
import net.dokilab.kezdomod.entity.client.SandstoneGolemModel;
import net.dokilab.kezdomod.entity.client.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KezdoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.HEXED_LAYER, HexedModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.SANDSTONEGOLEM_LAYER, SandstoneGolemModel::createBodyLayer);
    }
}
