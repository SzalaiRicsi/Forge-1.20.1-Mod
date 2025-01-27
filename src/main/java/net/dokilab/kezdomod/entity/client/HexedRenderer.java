package net.dokilab.kezdomod.entity.client;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.customs.HexedEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HexedRenderer extends MobRenderer<HexedEntity, HexedModel<HexedEntity>> {
    public HexedRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HexedModel<>(pContext.bakeLayer(ModModelLayers.HEXED_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(HexedEntity pEntity) {
        return new ResourceLocation(KezdoMod.MOD_ID,"textures/entity/hexed.png");
    }
}
