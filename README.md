# Lunchtime Jobs

<p>
  <a href="https://www.curseforge.com/minecraft/mc-mods/miso-jobs-careers">
    <img src="https://raw.githubusercontent.com/wtfyls/Lunchtime-Cozy-Create-Farming/main/assets/curseforgelarge.png" alt="Download on CurseForge" height="48">
  </a>
  &nbsp;
  <a href="https://discord.gg/68yhDdFpnx">
    <img src="https://raw.githubusercontent.com/wtfyls/Lunchtime-Cozy-Create-Farming/main/assets/discordlarge.png" alt="Join the Lunchtime Discord" height="48">
  </a>
  &nbsp;
  <a href="https://www.patreon.com/cw/Fierolam">
    <img src="https://raw.githubusercontent.com/wtfyls/Lunchtime-Cozy-Create-Farming/main/assets/patreonlarge.png" alt="Support Lunchtime on Patreon" height="48">
  </a>
  &nbsp;
  <a href="https://www.instagram.com/lunchbutt/">
    <img src="https://raw.githubusercontent.com/wtfyls/Lunchtime-Cozy-Create-Farming/main/assets/instagramlarge.png" alt="Follow Lunchtime on Instagram" height="48">
  </a>
  &nbsp;
  <a href="https://www.tiktok.com/@lunchbutt">
    <img src="https://raw.githubusercontent.com/wtfyls/Lunchtime-Cozy-Create-Farming/main/assets/tiktoklarge.png" alt="Lunchtime on TikTok" height="48">
  </a>
</p>

A standalone Minecraft profession, leveling, and progression mod for Minecraft Forge 1.20.1.

## Project Specifications

- **Minecraft Version:** 1.20.1
- **Forge Version:** 47.4.28
- **Java Version:** 17 (Required)
- **Mod ID:** `lunchtimejobs`
- **Mod Version:** `0.1.0-dev`
- **Package Base:** `com.lunchtime.jobs`
- **Main Class:** `com.lunchtime.jobs.LunchtimeJobs`

## Architecture Overview

Lunchtime Jobs uses a modular, server-authoritative architecture:

```
com.lunchtime.jobs/
├── LunchtimeJobs.java         # Main mod entry point and Forge event registration
├── config/                    # Forge configuration specifications (server-authoritative)
├── profession/                # Profession definitions, types, and core traits
├── experience/                # XP curves, gain calculation, and source processing
├── progression/               # Milestone tracking, perk logic, and rewards
├── player/                    # Server-authoritative capability and persistent data
├── networking/                # Bidirectional client/server packets
├── events/                    # Forge gameplay event subscribers and custom events
├── commands/                  # In-game admin and player commands
├── client/                    # Isolated client-only logic
│   ├── ClientSetup.java       # Client lifecycle setup
│   ├── rendering/             # Player overhead icons, world overlays
│   ├── hud/                   # XP popups and overlay HUD
│   └── screens/               # Job selection and management UI
├── integration/
│   └── embersquesting/        # Optional Ember's Questing integration bridge
├── registry/                  # DeferredRegisters for mod registry entries
└── data/                      # Data generators for recipes, tags, and datapacks
```

### Server Authority & Safety

- All player profession progression, experience calculations, and perks are strictly server-authoritative.
- The client receives state updates for HUD rendering and UI screens via synchronized network packets.
- Client-only code is isolated from common logic using `DistExecutor` and `ClientSetup` to avoid dedicated server crashes.

### Ember's Questing Compatibility

Lunchtime Jobs includes architecture for optional integration with [Ember's Questing](https://tysontheember.dev/embers-questing/) (Forge 1.20.1, release 1.1.0+).
- **Zero Mandatory Dependency:** Lunchtime Jobs is completely self-contained and functions fully without Ember's Questing.
- **Integration Points:** QuestApi, QuestEvents, custom tasks, conditions, and rewards are decoupled behind soft-dependency checks.

## Development Requirements

- **JDK 17** (Temurin, Zulu, or OpenJDK 17)
- **macOS Apple Silicon (aarch64) / Linux / Windows**

Set your Java 17 environment:
```bash
# macOS example:
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

## Building the Project

Ensure the Gradle wrapper is executable:
```bash
chmod +x gradlew
```

Build the mod JAR:
```bash
./gradlew clean build
```
Built artifacts are placed in `build/libs/`.

## Running the Development Client

To launch the Minecraft development client with the mod loaded:
```bash
./gradlew runClient
```

To launch a dedicated test server:
```bash
./gradlew runServer
```
