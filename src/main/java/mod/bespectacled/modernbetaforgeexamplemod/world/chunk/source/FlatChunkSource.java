package mod.bespectacled.modernbetaforgeexamplemod.world.chunk.source;

import java.util.List;

import mod.bespectacled.modernbetaforge.api.world.chunk.source.ChunkSource;
import mod.bespectacled.modernbetaforge.util.BlockStates;
import mod.bespectacled.modernbetaforge.util.chunk.HeightmapChunk.Type;
import mod.bespectacled.modernbetaforge.world.biome.ModernBetaBiomeProvider;
import mod.bespectacled.modernbetaforge.world.setting.ModernBetaGeneratorSettings;
import mod.bespectacled.modernbetaforgeexamplemod.ModernBetaExampleMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.StructureComponent;

public class FlatChunkSource extends ChunkSource {
    public static final ResourceLocation REGISTRY_KEY = ModernBetaExampleMod.createRegistryKey("flat");
    
    private final int height;
    
    public FlatChunkSource(long seed, ModernBetaGeneratorSettings settings) {
        super(seed, settings);
        
        this.height = settings.height;
    }
    
    @Override
    public int getSeaLevel() {
        return this.height;
    }
    
    @Override
    public void provideInitialChunk(World world, ChunkPrimer chunkPrimer, int chunkX, int chunkZ) {
        int startX = chunkX << 4;
        int startZ = chunkZ << 4;
        
        int seaLevel = this.getSeaLevel();
        
        for (int localZ = 0; localZ < 16; ++localZ) {
            int z = startZ + localZ;
            
            for (int localX = 0; localX < 16; ++localX) {
                int x = startX + localX;
                Biome biome = ((ModernBetaBiomeProvider)world.getBiomeProvider()).getBiomeSource().getBiome(x, z);
                
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
    public void provideProcessedChunk(World world, ChunkPrimer chunkPrimer, int chunkX, int chunkZ, List<StructureComponent> structureComponents) { }

    @Override
    public void provideSurface(World world, Biome[] biomes, ChunkPrimer chunkPrimer, int chunkX, int chunkZ) { }

    @Override
    public int getHeight(World world, int x, int z, Type type) {
        return this.height;
    }
}
