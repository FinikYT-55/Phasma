package net.finik_yt.phasmo.creativemodetab;

import net.finik_yt.phasmo.PhasmoMod;
import net.finik_yt.phasmo.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PhasmoMod.MOD_ID);


    public static final Supplier<CreativeModeTab> ITEMS_MODE_TAB = CREATIVE_MODE_TABS.register("items_mode_tab",
            () -> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(ModItems.EMF_DETECTOR.get()))
                    .title(Component.translatable("items.Tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.EMF_DETECTOR);
                        output.accept(ModItems.EMF_DETECTOR_II);
                        output.accept(ModItems.SPIRIT_BOX);


                    }).build()
            );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
