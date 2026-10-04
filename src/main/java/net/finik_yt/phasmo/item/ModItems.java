package net.finik_yt.phasmo.item;


import net.finik_yt.phasmo.PhasmoMod;
import net.finik_yt.phasmo.item.custom.EMFDetector;
import net.finik_yt.phasmo.item.custom.EMFDetectorII;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PhasmoMod.MOD_ID);

    public static final DeferredItem<Item> EMF_DETECTOR = ITEMS.registerItem("emf_detector",
            properties -> new EMFDetector(properties.stacksTo(1)));
    public static final DeferredItem<Item> SPIRIT_BOX = ITEMS.registerItem("spirit_box",
            properties -> new Item(properties.stacksTo(1)));

    public static final DeferredItem<Item> EMF_DETECTOR_II = ITEMS.registerItem("emf_detector_ii",
            properties -> new EMFDetectorII(properties.stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}