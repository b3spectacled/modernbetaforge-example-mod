package mod.bespectacled.modernbetaforgeexamplemod;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.bespectacled.modernbetaforge.api.registry.ModernBetaRegistries;
import mod.bespectacled.modernbetaforge.api.world.setting.BiomeProperty;
import mod.bespectacled.modernbetaforge.api.world.setting.IntProperty;
import mod.bespectacled.modernbetaforge.api.world.setting.PropertyGuiType;
import mod.bespectacled.modernbetaforgeexamplemod.world.biome.source.CheckerboardBiomeSource;
import mod.bespectacled.modernbetaforgeexamplemod.world.chunk.source.FlatChunkSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = ModernBetaExampleMod.MODID,
    name = ModernBetaExampleMod.NAME,
    version = ModernBetaExampleMod.VERSION,
    acceptedMinecraftVersions = ModernBetaExampleMod.MCVERSION,
    dependencies = ModernBetaExampleMod.MODERN_BETA_MODID
)
public class ModernBetaExampleMod {
    public static final String MODID = "modernbetaforgeexamplemod";
    public static final String NAME = "Modern Beta Forge Example Mod";
    public static final String VERSION = "1.0.0.0";
    public static final String MCVERSION = "1.12.2";
    public static final String MODERN_BETA_MODID = "required-after:modernbetaforge@[1.4.0.0,);";

    private static final Logger LOGGER = LogManager.getLogger(MODID);
    
    public static void log(Level level, String message) {
        LOGGER.log(level, "{}", message);
    }
    
    public static ResourceLocation createRegistryKey(String name) {
        return new ResourceLocation(MODID, name);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) { }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModernBetaRegistries.CHUNK.register(createRegistryKey("flat"), FlatChunkSource::new);
        ModernBetaRegistries.BIOME.register(createRegistryKey("checkerboard"), CheckerboardBiomeSource::new);
        
        ModernBetaRegistries.PROPERTY.register(
            createRegistryKey(FlatChunkSource.FLAT_HEIGHT),
            new IntProperty(64, 0, 255, PropertyGuiType.SLIDER)
        );
        ModernBetaRegistries.PROPERTY.register(
            createRegistryKey(CheckerboardBiomeSource.BIOME_0_ID),
            new BiomeProperty("minecraft:desert")
        );
        ModernBetaRegistries.PROPERTY.register(
            createRegistryKey(CheckerboardBiomeSource.BIOME_1_ID),
            new BiomeProperty("minecraft:forest")
        );
    }
}
