package com.lunchtime.jobs;

import com.lunchtime.jobs.client.ClientSetup;
import com.lunchtime.jobs.config.ModConfig;
import com.lunchtime.jobs.integration.embersquesting.EmbersQuestingIntegration;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Main mod entry point for Lunchtime Jobs.
 * Initializes core registries, lifecycle events, configuration, and prepares
 * server-authoritative profession and progression systems.
 */
@Mod(LunchtimeJobs.MODID)
public class LunchtimeJobs {
    public static final String MODID = "lunchtimejobs";
    public static final String MOD_NAME = "Lunchtime Jobs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LunchtimeJobs(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Lifecycle event listeners
        modEventBus.addListener(this::commonSetup);

        // Register Forge configuration specification
        context.registerConfig(
                net.minecraftforge.fml.config.ModConfig.Type.COMMON,
                ModConfig.SPEC,
                "lunchtimejobs-common.toml"
        );

        // Client initialization safely isolated to the physical client
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientSetup.init(modEventBus));

        // Register to Forge gameplay event bus
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("Initialized {} common systems.", MOD_NAME);
            EmbersQuestingIntegration.initIfPresent();
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("{} server starting - preparing server-authoritative progression systems.", MOD_NAME);
    }
}
