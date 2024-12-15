package mod.bespectacled.modernbetaforgeexamplemod.world.biome.source;

import mod.bespectacled.modernbetaforge.api.world.biome.BiomeSource;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.WorldInfo;

public class CheckerboardBiomeSource extends BiomeSource {
    private final Biome biome0;
    private final Biome biome1;

    public CheckerboardBiomeSource(WorldInfo worldInfo) {
        super(worldInfo);
        
        this.biome0 = Biomes.PLAINS;
        this.biome1 = Biomes.DESERT;
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
