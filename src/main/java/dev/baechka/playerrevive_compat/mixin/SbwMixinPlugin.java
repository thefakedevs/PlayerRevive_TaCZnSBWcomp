package dev.baechka.playerrevive_compat.mixin;

import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Mixin плагин для условной загрузки миксинов SuperbWarfare
 * Загружает миксины только если SuperbWarfare присутствует
 */
public class SbwMixinPlugin implements IMixinConfigPlugin {

    private static final boolean SBW_LOADED;

    static {
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
        return SBW_LOADED;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        // Если SBW не загружен, возвращаем пустой список - миксины не будут даже пытаться загружаться
        if (!SBW_LOADED) {
            return Collections.emptyList();
        }
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
