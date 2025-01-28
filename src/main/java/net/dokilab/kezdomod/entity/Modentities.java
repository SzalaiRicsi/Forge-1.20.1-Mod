package net.dokilab.kezdomod.entity;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.entity.customs.HexedEntity;
import net.dokilab.kezdomod.entity.customs.SandstoneGolemEntity;
import net.dokilab.kezdomod.entity.customs.LurkerEntity;
import net.dokilab.kezdomod.entity.customs.MummyEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.nio.charset.MalformedInputException;

public class Modentities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KezdoMod.MOD_ID);

    public static final RegistryObject<EntityType<HexedEntity>> HEXED = ENTITY_TYPES.register("hexed",
            () -> EntityType.Builder.of(HexedEntity::new, MobCategory.MONSTER)
                    .sized(0.7f, 2.2f).build("hexed"));

    public static final RegistryObject<EntityType<SandstoneGolemEntity>> SANDSTONE_GOLEM = ENTITY_TYPES.register("sandstone_golem",
            () -> EntityType.Builder.of(SandstoneGolemEntity::new, MobCategory.CREATURE)
                    .sized(0.7f, 2.2f).build("sandstone_golem"));

    public static final RegistryObject<EntityType<LurkerEntity>> LURKER = ENTITY_TYPES.register("lurker",
            () -> EntityType.Builder.of(LurkerEntity::new, MobCategory.MONSTER)
                    .sized(0.7f, 2.2f).build("lurker"));

    public static final RegistryObject<EntityType<MummyEntity>> MUMMY = ENTITY_TYPES.register("mummy",
            () -> EntityType.Builder.of(MummyEntity::new, MobCategory.MONSTER)
                    .sized(0.7f, 2.2f).build("mummy"));



    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
