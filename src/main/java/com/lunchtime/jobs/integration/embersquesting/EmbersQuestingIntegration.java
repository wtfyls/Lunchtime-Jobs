package com.lunchtime.jobs.integration.embersquesting;

import com.lunchtime.jobs.LunchtimeJobs;
import net.minecraftforge.fml.ModList;

/**
 * Optional integration coordinator for Ember's Questing (https://tysontheember.dev/embers-questing/).
 *
 * CRITICAL ARCHITECTURAL CONSTRAINTS:
 * 1. Ember's Questing is strictly optional; Lunchtime Jobs functions completely without it.
 * 2. No hard dependencies, classes, or imports of Ember's Questing exist in the core mod runtime.
 * 3. Planned integration points for Ember's Questing (release 1.1.0+):
 *    - QuestApi / QuestEvents for triggering profession XP or quest task completion on progression
 *    - CustomConditions: checking player profession level or unlocked perks
 *    - CustomTask: profession-specific tasks (e.g. reach Miner level 10, craft N items in Blacksmith)
 *    - CustomReward: rewarding profession XP or unlocking professions/perks
 */
public final class EmbersQuestingIntegration {

    public static final String MOD_ID = "embersquesting";

    private EmbersQuestingIntegration() {
    }

    /**
     * Checks whether Ember's Questing is present in the active mod list.
     *
     * @return true if Ember's Questing is loaded, false otherwise.
     */
    public static boolean isLoaded() {
        return ModList.get().isLoaded(MOD_ID);
    }

    /**
     * Called during common setup to safely discover and prepare the optional integration.
     */
    public static void initIfPresent() {
        if (isLoaded()) {
            LunchtimeJobs.LOGGER.info("Ember's Questing detected. Optional integration hook will be available.");
            // Actual API hook classes will be loaded through reflection or isolated bridge classes
            // when quest integration is implemented in a future milestone.
        } else {
            LunchtimeJobs.LOGGER.debug("Ember's Questing is not present. Running standalone.");
        }
    }
}
