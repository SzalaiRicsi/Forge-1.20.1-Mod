package net.dokilab.kezdomod.entity.client;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.customs.LurkerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LurkerRenderer extends MobRenderer<LurkerEntity, LurkerModel<LurkerEntity>> {
    public LurkerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LurkerModel<>(pContext.bakeLayer(ModModelLayers.LURKER_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(LurkerEntity pEntity) {
        return new ResourceLocation(KezdoMod.MOD_ID,"textures/entity/lurker.png");
    }
}
