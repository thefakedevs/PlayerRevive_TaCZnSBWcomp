package dev.baechka.playerrevive_compat.mixin;

import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Mixin плагин для условной загрузки миксинов
 * Загружает миксины только если целевые моды присутствуют
 */
public class MixinPlugin implements IMixinConfigPlugin {

    private static final boolean TACZ_LOADED;
    private static final boolean SBW_LOADED;

    static {
        TACZ_LOADED = LoadingModList.get().getModFileById("tacz") != null;
        SBW_LOADED = LoadingModList.get().getModFileById("superbwarfare") != null;
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Миксины для TaCZ
        if (mixinClassName.contains("ClientMessagePlayerShootMixin") ||
            mixinClassName.contains("LocalPlayerShootMixin") ||
            mixinClassName.contains("ShootKeyMixin") ||
            mixinClassName.contains("HitboxHelperMixin")) {
            return TACZ_LOADED;
        }

        // Миксины для SuperbWarfare
        if (mixinClassName.contains("ShootMessageMixin") ||
            mixinClassName.contains("ClientEventHandlerMixin") ||
            mixinClassName.contains("ClickHandlerMixin")) {
            return SBW_LOADED;
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
