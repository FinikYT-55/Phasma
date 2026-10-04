package net.finik_yt.phasmo.item.custom;

import java.util.List;

import net.finik_yt.phasmo.data.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class EMFDetectorII extends Item {

    /** радиус обнаружения жителя-призрака */
    private static final double RANGE = 5.0D;
    /** проверка раз в 10 тиков (0,5 секунды) */
    private static final int CHECK_INTERVAL = 2;
    /** шанс события в процентах, если рядом улика */
    private static final int EVENT_CHANCE = 50;
    /** шанс пятёрки при ролле, если у жителя есть улика ЭМП 5 */
    private static final float EMF5_CHANCE = 0.33F;
    /** собственный генератор случайных чисел — не зависит от доступа к level.random */
    private static final RandomSource RANDOM = RandomSource.create();

    public EMFDetectorII(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, level, owner, slot);

        if (!(owner instanceof Player player)) {
            return;
        }
        if (level.getGameTime() % CHECK_INTERVAL != 0) {
            return;
        }

        Boolean power = stack.get(ModDataComponents.POWER.value());
        if (power == null || !power) {
            return;
        }

        // ищем ближайшего жителя с уликой ЭМП 5
        Entity evidenceSource = null;
        List<LivingEntity> mobs = level.getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(RANGE));
        for (LivingEntity mob : mobs) {
            String typeKey = EntityType.getKey(mob.getType()).getPath();
            if (typeKey.equals("villager") && mob.getPersistentData().contains("phasma_emf5")) {
                evidenceSource = mob;
                break;
            }
        }
        if (evidenceSource == null) {
            return;
        }

        // событие срабатывает не каждый тик, а с вероятностью
        if (RANDOM.nextInt(100) >= EVENT_CHANCE) {
            return;
        }

        // ролл уровня: с уликой возможна пятёрка, без — только 2-4
        byte emf;
        if (RANDOM.nextFloat() < EMF5_CHANCE) {
            emf = 5;
        } else {
            emf = (byte) (2 + RANDOM.nextInt(3));
        }

        stack.set(ModDataComponents.EMP_LVL.value(), emf);

        // звуковой и текстовый отклик
        level.playSound(null, player.blockPosition(), SoundEvents.NOTE_BLOCK_HAT.value(),
                SoundSource.PLAYERS, 0.7F, 0.5F + emf * 0.12F);
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
