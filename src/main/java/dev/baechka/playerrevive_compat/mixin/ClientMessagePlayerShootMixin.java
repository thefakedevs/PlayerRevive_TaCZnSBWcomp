package dev.baechka.playerrevive_compat.mixin;

import com.tacz.guns.network.message.ClientMessagePlayerShoot;
import dev.baechka.playerrevive_compat.util.BleedingHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

/**
 * Миксин для блокировки стрельбы TaCZ на сервере
 * когда игрок находится в состоянии bleeding
 */
@Mixin(value = ClientMessagePlayerShoot.class, remap = false)
public class ClientMessagePlayerShootMixin {

    @Inject(method = "handle", at = @At("HEAD"), cancellable = true)
    private static void onHandle(ClientMessagePlayerShoot message, Supplier<NetworkEvent.Context> contextSupplier, CallbackInfo ci) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isServer()) {
            ServerPlayer player = context.getSender();

            if (player != null && BleedingHelper.isBleeding(player)) {
                context.setPacketHandled(true);
                ci.cancel();
            }
        }
    }
}
