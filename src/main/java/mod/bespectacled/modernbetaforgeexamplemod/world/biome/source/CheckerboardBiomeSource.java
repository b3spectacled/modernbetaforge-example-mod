package mod.bespectacled.modernbetaforgeexamplemod.world.biome.source;

import mod.bespectacled.modernbetaforge.api.world.biome.BiomeSource;
import mod.bespectacled.modernbetaforge.world.setting.ModernBetaGeneratorSettings;
import mod.bespectacled.modernbetaforgeexamplemod.ModernBetaExampleMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class CheckerboardBiomeSource extends BiomeSource {
    public static final ResourceLocation REGISTRY_KEY = ModernBetaExampleMod.createRegistryKey("checkerboard");
    public static final ResourceLocation BIOME_0_KEY = ModernBetaExampleMod.createRegistryKey("checkerboardBiome0");
    public static final ResourceLocation BIOME_1_KEY = ModernBetaExampleMod.createRegistryKey("checkerboardBiome1");
    
    private final Biome biome0;
    private final Biome biome1;

    public CheckerboardBiomeSource(long seed, ModernBetaGeneratorSettings settings) {
        super(seed, settings);

        this.biome0 = settings.getBiomeProperty(BIOME_0_KEY);
        this.biome1 = settings.getBiomeProperty(BIOME_1_KEY);
    }

    @Override
    public Biome getBiome(int x, int z) {
        int chunkX = x >> 4;
        int chunkZ = z >> 4;
        
        boolean evenX = chunkX % 2 == 0;
        boolean evenZ = chunkZ % 2 == 0;
        
        if (evenX && !evenZ || !evenX && evenZ) {
            return this.biome0;
        }
        
        return this.biome1;
    }
}
