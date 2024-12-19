package mod.bespectacled.modernbetaforgeexamplemod.world.chunk.source;

import mod.bespectacled.modernbetaforge.api.world.chunk.ChunkSource;
import mod.bespectacled.modernbetaforge.util.BlockStates;
import mod.bespectacled.modernbetaforge.util.chunk.HeightmapChunk.Type;
import mod.bespectacled.modernbetaforge.world.chunk.ModernBetaChunkGenerator;
import mod.bespectacled.modernbetaforge.world.setting.ModernBetaGeneratorSettings;
import mod.bespectacled.modernbetaforgeexamplemod.ModernBetaExampleMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class FlatChunkSource extends ChunkSource {
    public static final String FLAT_HEIGHT = "flatHeight";
    
    private final int height;
    
    public FlatChunkSource(
        World world,
        ModernBetaChunkGenerator chunkGenerator,
        ModernBetaGeneratorSettings settings
    ) {
        super(world, chunkGenerator, settings);
        
        this.height = settings.getCustomInt(ModernBetaExampleMod.createRegistryKey(FLAT_HEIGHT));
    }
    
    @Override
    public int getSeaLevel() {
        return this.height;
    }

    @Override
    public int getHeight(int chunkX, int chunkZ, Type type) {
        return this.height;
    }

    @Override
    public void provideInitialChunk(ChunkPrimer chunkPrimer, int chunkX, int chunkZ) {
        int startX = chunkX << 4;
        int startZ = chunkZ << 4;
        
        int seaLevel = this.getSeaLevel();
        
        for (int localZ = 0; localZ < 16; ++localZ) {
            int z = startZ + localZ;
            
            for (int localX = 0; localX < 16; ++localX) {
                int x = startX + localX;
                Biome biome = this.biomeProvider.getBiomeSource().getBiome(x, z);
                
                for (int y = 0; y <= this.worldHeight; ++y) {
                    IBlockState blockState = BlockStates.AIR;
                    
                    if (y == 0) {
                        blockState = BlockStates.BEDROCK;
                    } else if (y < seaLevel - 2) {
                        blockState = BlockStates.STONE;
                    } else if (y < seaLevel) {
                        blockState = biome.fillerBlock;
                    } else if (y == seaLevel) {
                        blockState = biome.topBlock;
                    }
                    
                    chunkPrimer.setBlockState(localX, y, localZ, blockState);
                }
            }
        }
    }
    
    @Override
    public void provideProcessedChunk(ChunkPrimer chunkPrimer, int chunkX, int chunkZ) { }

    @Override
    public void provideSurface(Biome[] biomes, ChunkPrimer chunkPrimer, int chunkX, int chunkZ) { }
}
