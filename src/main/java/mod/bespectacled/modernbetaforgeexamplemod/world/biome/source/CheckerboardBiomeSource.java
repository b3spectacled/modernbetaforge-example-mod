package mod.bespectacled.modernbetaforgeexamplemod.world.biome.source;

import mod.bespectacled.modernbetaforge.api.world.biome.BiomeSource;
import mod.bespectacled.modernbetaforge.world.setting.ModernBetaGeneratorSettings;
import mod.bespectacled.modernbetaforgeexamplemod.ModernBetaExampleMod;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.WorldInfo;

public class CheckerboardBiomeSource extends BiomeSource {
    public static final String BIOME_0_ID = "checkerboardBiome0";
    public static final String BIOME_1_ID = "checkerboardBiome1";
    
    private final Biome biome0;
    private final Biome biome1;

    public CheckerboardBiomeSource(WorldInfo worldInfo) {
        super(worldInfo);
        
        ModernBetaGeneratorSettings settings = ModernBetaGeneratorSettings.build(worldInfo.getGeneratorOptions());
        this.biome0 = settings.getCustomBiome(ModernBetaExampleMod.createRegistryKey(BIOME_0_ID));
        this.biome1 = settings.getCustomBiome(ModernBetaExampleMod.createRegistryKey(BIOME_1_ID));
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
