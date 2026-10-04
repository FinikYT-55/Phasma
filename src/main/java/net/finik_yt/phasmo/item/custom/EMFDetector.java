package net.finik_yt.phasmo.item.custom;

import net.finik_yt.phasmo.data.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class EMFDetector extends Item {
    public EMFDetector(Properties properties) {
        super(properties);
    }
    // радиус поиска в блоках
    private static final double RANGE = 10.0D;
    // проверять каждые 10 тиков (0,5 секунды), чтобы не грузить сервер
    private static final int CHECK_INTERVAL = 10;

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {



    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            Boolean power = stack.get(ModDataComponents.POWER.value());
            boolean wasOn = power != null && power;

            stack.set(ModDataComponents.POWER.value(), !wasOn);
            stack.set(ModDataComponents.EMP_LVL.value(), (byte) 0);
        }
        return InteractionResult.SUCCESS;
    }
}
