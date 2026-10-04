package net.finik_yt.phasmo;

import net.finik_yt.phasmo.creativemodetab.ModCreativeModeTabs;
import net.finik_yt.phasmo.data.ModDataComponents;
import net.finik_yt.phasmo.item.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(PhasmoMod.MOD_ID)
public class PhasmoMod {
    public static final String MOD_ID = "phasmo";
    public static final Logger LOGGER = LogUtils.getLogger();

    // Конструктор для класса mod - это первый код, который запускается при загрузке вашего мода.
    // FML распознает некоторые типы параметров, такие как IEventBus или ModContainer, и автоматически передает их.
    public PhasmoMod(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModDataComponents.register(modEventBus);
    }
}
