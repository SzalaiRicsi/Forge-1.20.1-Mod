package net.dokilab.kezdomod.entity.client;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.customs.HexedEntity;
import net.dokilab.kezdomod.entity.customs.SandstoneGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SandstoneGolemRenderer extends MobRenderer<SandstoneGolemEntity, SandstoneGolemModel<SandstoneGolemEntity>> {
    public SandstoneGolemRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SandstoneGolemModel<>(pContext.bakeLayer(ModModelLayers.SANDSTONEGOLEM_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(SandstoneGolemEntity pEntity) {
        return new ResourceLocation(KezdoMod.MOD_ID,"textures/entity/sandstone_golem.png");
    }
}
