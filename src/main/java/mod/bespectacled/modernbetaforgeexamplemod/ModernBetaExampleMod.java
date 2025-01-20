package mod.bespectacled.modernbetaforgeexamplemod;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.bespectacled.modernbetaforge.api.property.BiomeProperty;
import mod.bespectacled.modernbetaforge.api.registry.ModernBetaRegistries;
import mod.bespectacled.modernbetaforgeexamplemod.world.biome.source.CheckerboardBiomeSource;
import mod.bespectacled.modernbetaforgeexamplemod.world.chunk.source.FlatChunkSource;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
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
    public static final String MODERN_BETA_MODID = "required-after:modernbetaforge@[1.5.0.0,1.6.0.0);";

    private static final Logger LOGGER = LogManager.getLogger(MODID);
    
    public static void log(Level level, String message) {
        LOGGER.log(level, "{}", message);
    }
    
    public static ResourceLocation createRegistryKey(String name) {
        return new ResourceLocation(MODID, name);
    }
    
    @SidedProxy(
        clientSide = "mod.bespectacled.modernbetaforgeexamplemod.ModernBetaExampleModClientProxy",
        serverSide = "mod.bespectacled.modernbetaforgeexamplemod.ModernBetaExampleModServerProxy"
    )
    public static ModernBetaExampleModProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) { }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModernBetaRegistries.CHUNK_SOURCE.register(FlatChunkSource.REGISTRY_KEY, FlatChunkSource::new);
        ModernBetaRegistries.BIOME_SOURCE.register(CheckerboardBiomeSource.REGISTRY_KEY, CheckerboardBiomeSource::new);

        ModernBetaRegistries.PROPERTY.register(
            CheckerboardBiomeSource.BIOME_0_KEY,
            new BiomeProperty(Biomes.DESERT.getRegistryName())
        );
        ModernBetaRegistries.PROPERTY.register(
            CheckerboardBiomeSource.BIOME_1_KEY,
            new BiomeProperty(Biomes.PLAINS.getRegistryName())
        );
        
        proxy.init();
    }
}
