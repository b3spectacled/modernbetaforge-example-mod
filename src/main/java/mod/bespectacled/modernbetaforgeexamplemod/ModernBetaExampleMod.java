package mod.bespectacled.modernbetaforgeexamplemod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.bespectacled.modernbetaforge.api.registry.ModernBetaRegistries;
import mod.bespectacled.modernbetaforgeexamplemod.world.biome.source.CheckerboardBiomeSource;
import mod.bespectacled.modernbetaforgeexamplemod.world.chunk.source.FlatChunkSource;

@Mod(
    modid = ModernBetaExampleMod.MODID,
    name = ModernBetaExampleMod.NAME,
    version = ModernBetaExampleMod.VERSION,
    acceptedMinecraftVersions = ModernBetaExampleMod.MCVERSION,
    dependencies = ModernBetaExampleMod.MODERN_BETA_MODID
)
public class ModernBetaExampleMod {
    public static final String MODID = "modernbetaforge-example-mod";
    public static final String NAME = "Modern Beta Forge Example Mod";
    public static final String VERSION = "1.0.0.0";
    public static final String MCVERSION = "1.12.2";
    public static final String MODERN_BETA_MODID = "required-after:modernbetaforge;";

    private static final Logger LOGGER = LogManager.getLogger(MODID);
    
    public static void log(Level level, String message) {
        LOGGER.log(level, "{}", message);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) { }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModernBetaRegistries.CHUNK.register("flat", FlatChunkSource::new);
        ModernBetaRegistries.BIOME.register("checkerboard", CheckerboardBiomeSource::new);
    }
}
