package net.dokilab.kezdomod.item;

import net.dokilab.kezdomod.KezdoMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KezdoMod.MOD_ID);

//    Items:

    public static final RegistryObject<Item> Coin = ITEMS.register("coin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Coin_Silver = ITEMS.register("coin_silver",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Coin_Gold_Pouch = ITEMS.register("coin_gold_pouch",
            () -> new Item(new Item.Properties()));

//    END

    public static void  register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
