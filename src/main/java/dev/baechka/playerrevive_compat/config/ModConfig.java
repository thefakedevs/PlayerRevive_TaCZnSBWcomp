package dev.baechka.playerrevive_compat.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    
    public static final GeneralConfig GENERAL;
    public static final ForgeConfigSpec SPEC;
    
    static {
        GENERAL = new GeneralConfig(BUILDER);
        SPEC = BUILDER.build();
    }
    
    public static class GeneralConfig {
        public final ForgeConfigSpec.BooleanValue canShootWhenDowned;

        GeneralConfig(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            
            canShootWhenDowned = builder
                    .comment("When true, downed (bleeding) players can still shoot their weapons")
                    .define("canShootWhenDowned", false);

            builder.pop();
        }
    }
}
