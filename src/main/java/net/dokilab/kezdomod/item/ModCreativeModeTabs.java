package net.dokilab.kezdomod.item;

import net.dokilab.kezdomod.KezdoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KezdoMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> Kezdo_Tab = CREATIVE_MODE_TABS.register("kezdo_tab",
            () ->  CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.Coin.get()))
                    .title(Component.translatable("creativetab.kezdo_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Moditems.Coin_Silver.get());
                        pOutput.accept(Moditems.Coin.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
