package net.finik_yt.phasmo.data;

import com.mojang.serialization.Codec;
import net.finik_yt.phasmo.PhasmoMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
    DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, PhasmoMod.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> POWER = register("power",
            builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Byte>> EMP_LVL = register("emp_lvl",
            builder -> builder.persistent(Codec.BYTE).networkSynchronized(ByteBufCodecs.BYTE)
    );










    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                  UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, ()-> builderOperator.apply(DataComponentType.builder()).build());
    }
    public static void register(IEventBus eventBus){
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
