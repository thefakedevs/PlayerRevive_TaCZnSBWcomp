package dev.baechka.playerrevive_compat.client;

import com.tacz.guns.api.event.common.GunFireEvent;
import com.tacz.guns.api.event.common.GunShootEvent;
import dev.baechka.playerrevive_compat.util.BleedingHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Клиентские отмены событий TaCZ, чтобы анимации/звук не запускались
 */
@Mod.EventBusSubscriber(modid = "playerrevive_compat", value = Dist.CLIENT)
public class TaczClientEventCanceller {

    @SubscribeEvent
    public static void onGunShoot(GunShootEvent event) {
        if (event.getShooter() instanceof Player player && BleedingHelper.isBleeding(player)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onGunFire(GunFireEvent event) {
        if (event.getShooter() instanceof Player player && BleedingHelper.isBleeding(player)) {
            event.setCanceled(true);
        }
    }
}
