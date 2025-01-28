package net.dokilab.kezdomod.block;

import net.dokilab.kezdomod.KezdoMod;
import net.dokilab.kezdomod.block.custom.HorussblessingBlock;
import net.dokilab.kezdomod.item.Moditems;
import net.dokilab.kezdomod.sound.Modsounds;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Modblocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KezdoMod.MOD_ID);

//    BLOKOk

    public static final RegistryObject<Block> Coin_Gold_Sack = registerBlock("coin_gold_sack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).noOcclusion().sound(Modsounds.coin_gold_sack_sounds)));

    public static final RegistryObject<Block> Horuss_Block = registerBlock("horuss_block",
            () -> new HorussblessingBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).noOcclusion().dynamicShape()));

    public static final RegistryObject<Block> Azurite_Sand = registerBlock("azurite_sand",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.SAND), UniformInt.of(3,6)));

    public static final RegistryObject<Block> Azurite_Block = registerBlock("azurite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).noOcclusion()));

//    END

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return Moditems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
