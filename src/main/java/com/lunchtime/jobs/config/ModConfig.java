package com.lunchtime.jobs.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Configuration specification for Lunchtime Jobs.
 * progression and gameplay configuration will be server-authoritative.
 */
public final class ModConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ENABLE_DEBUG_LOGGING;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Lunchtime Jobs - General Configuration").push("general");
        ENABLE_DEBUG_LOGGING = builder
                .comment("Enable verbose debug logging for Lunchtime Jobs systems (progression, XP, networking).")
                .define("enableDebugLogging", false);
        builder.pop();

        SPEC = builder.build();
    }

    private ModConfig() {
    }
}
