package net.dokilab.kezdomod.item;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.item.custom.FuelItem;
import net.dokilab.kezdomod.item.custom.StaffOfSandItem;
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

    public static final RegistryObject<Item> Fresh_Date = ITEMS.register("fresh_date",
            () -> new Item(new Item.Properties().food(Modsfood.FRESH_DATE).stacksTo(16)));

    public static final RegistryObject<Item> Date = ITEMS.register("date",
            () -> new Item(new Item.Properties().food(Modsfood.DATE).stacksTo(16)));

    public static final RegistryObject<Item> Azurite = ITEMS.register("azurite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> StaffofSand = ITEMS.register("staffofsand",
            () -> new StaffOfSandItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> Dung = ITEMS.register("dung",
            () -> new FuelItem(new Item.Properties(), 800));

//    END

    public static void  register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
