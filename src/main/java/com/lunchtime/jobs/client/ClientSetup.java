package com.lunchtime.jobs.client;

import com.lunchtime.jobs.LunchtimeJobs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Handles client-side initialization for Lunchtime Jobs.
 * Isolated from dedicated servers to prevent any accidental classloading crashes.
 */
@OnlyIn(Dist.CLIENT)
public final class ClientSetup {

    private ClientSetup() {
    }

    /**
     * Registers client-specific event handlers on the mod event bus.
     */
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(ClientSetup::onClientSetup);
    }

    private static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            LunchtimeJobs.LOGGER.info("Initialized client systems for Lunchtime Jobs.");
        });
    }
}
