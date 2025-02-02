package net.dokilab.kezdomod;

import com.mojang.logging.LogUtils;
import net.dokilab.kezdomod.block.Modblocks;
import net.dokilab.kezdomod.entity.Modentities;
import net.dokilab.kezdomod.entity.client.HexedRenderer;
import net.dokilab.kezdomod.entity.client.SandstoneGolemRenderer;
import net.dokilab.kezdomod.entity.client.LurkerRenderer;
import net.dokilab.kezdomod.entity.client.MummyRenderer;
import net.dokilab.kezdomod.item.ModCreativeModeTabs;
import net.dokilab.kezdomod.item.Moditems;
import net.dokilab.kezdomod.sound.Modsounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import javax.swing.text.html.parser.Entity;

// The value here should match an entry in the META-INF/mods.toml
@Mod(KezdoMod.MOD_ID)
public class KezdoMod
{
    public static final String MOD_ID = "kezdomod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public KezdoMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        Moditems.register(modEventBus);
        Modblocks.register(modEventBus);
        Modsounds.register(modEventBus);
        Modentities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
        event.accept(Moditems.Coin);
        event.accept(Moditems.Coin_Silver);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(Modentities.HEXED.get(), HexedRenderer::new);

            EntityRenderers.register(Modentities.SANDSTONE_GOLEM.get(), SandstoneGolemRenderer::new);

            EntityRenderers.register(Modentities.LURKER.get(), LurkerRenderer::new);

            EntityRenderers.register(Modentities.MUMMY.get(), MummyRenderer::new);
        }
    }
}
