package net.dokilab.kezdomod.entity.client;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.customs.LurkerEntity;
import net.dokilab.kezdomod.entity.customs.MummyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MummyRenderer extends MobRenderer<MummyEntity, MummyModel<MummyEntity>> {
    public MummyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MummyModel<>(pContext.bakeLayer(ModModelLayers.MUMMY_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(MummyEntity pEntity) {
        return new ResourceLocation(KezdoMod.MOD_ID,"textures/entity/mummy.png");
    }
}
