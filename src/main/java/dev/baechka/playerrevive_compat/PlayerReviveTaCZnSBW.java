package dev.baechka.playerrevive_compat;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(PlayerReviveTaCZnSBW.MODID)
public class PlayerReviveTaCZnSBW {
    public static final String MODID = "playerrevive_tacz_sbw_compitability";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PlayerReviveTaCZnSBW() {
        LOGGER.info("PlayerRevive TaCZ/SBW Compatibility mod initializing...");

        boolean playerReviveLoaded = ModList.get().isLoaded("playerrevive");
        boolean taczLoaded = ModList.get().isLoaded("tacz");
        boolean sbwLoaded = ModList.get().isLoaded("superbwarfare");

        LOGGER.info("PlayerRevive loaded: {}", playerReviveLoaded);
        LOGGER.info("TaCZ loaded: {}", taczLoaded);
        LOGGER.info("SuperbWarfare loaded: {}", sbwLoaded);

        if (!playerReviveLoaded) {
            LOGGER.warn("PlayerRevive not found! This mod requires PlayerRevive to function.");
            return;
        }

        if (taczLoaded) {
            LOGGER.info("Registering TaCZ compatibility handler");
            registerTaczHandler();
        }

        // SuperbWarfare compatibility is handled through mixins
        if (sbwLoaded) {
            LOGGER.info("SuperbWarfare compatibility active (via mixins)");
        }

        if (taczLoaded || sbwLoaded) {
            LOGGER.info("Compatibility layer active - players in bleeding state will be unable to shoot");
        } else {
            LOGGER.warn("Neither TaCZ nor SuperbWarfare found! This mod has no effect.");
        }
    }

    private void registerTaczHandler() {
        try {
            Class<?> handlerClass = Class.forName("dev.baechka.playerrevive_compat.handler.TaczEventHandler");
            MinecraftForge.EVENT_BUS.register(handlerClass);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Failed to load TaCZ handler class", e);
        }
    }
}
