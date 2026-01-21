package dev.baechka.playerrevive_compat.handler;

import com.tacz.guns.api.event.common.GunFireEvent;
import com.tacz.guns.api.event.common.GunShootEvent;
import dev.baechka.playerrevive_compat.util.BleedingHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Обработчик событий для мода TaCZ (Timeless and Classics Zero)
 * Блокирует стрельбу если игрок в состоянии bleeding
 *
 * Этот класс регистрируется вручную только если TaCZ загружен
 */
public class TaczEventHandler {

    /**
     * Отменяет событие GunShootEvent если игрок в состоянии bleeding
     * Это событие срабатывает при нажатии на кнопку стрельбы (один раз)
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onGunShoot(GunShootEvent event) {
        if (event.getShooter() instanceof Player player) {
            if (BleedingHelper.isBleeding(player)) {
                event.setCanceled(true);
            }
        }
    }

    /**
     * Отменяет событие GunFireEvent если игрок в состоянии bleeding
     * Это событие срабатывает при каждом выстреле (может быть несколько раз в burst режиме)
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onGunFire(GunFireEvent event) {
        if (event.getShooter() instanceof Player player) {
            if (BleedingHelper.isBleeding(player)) {
                event.setCanceled(true);
            }
        }
    }
}
