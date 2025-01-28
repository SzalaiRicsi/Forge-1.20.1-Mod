package net.dokilab.kezdomod.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.dokilab.kezdomod.KezdoMod;

public class ModModelLayers {
    public static final ModelLayerLocation HEXED_LAYER =
            new ModelLayerLocation(new ResourceLocation(KezdoMod.MOD_ID, "hexed_layer"), "main");

    public static final ModelLayerLocation SANDSTONEGOLEM_LAYER =
            new ModelLayerLocation(new ResourceLocation(KezdoMod.MOD_ID, "sandstone_layer"), "main");

    public static final ModelLayerLocation LURKER_LAYER =
            new ModelLayerLocation(new ResourceLocation(KezdoMod.MOD_ID, "lurker_layer"), "main");

    public static final ModelLayerLocation MUMMY_LAYER =
            new ModelLayerLocation(new ResourceLocation(KezdoMod.MOD_ID, "mummy_layer"), "main");
}

