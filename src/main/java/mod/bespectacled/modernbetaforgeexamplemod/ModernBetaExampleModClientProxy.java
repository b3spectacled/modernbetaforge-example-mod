package mod.bespectacled.modernbetaforgeexamplemod;

import mod.bespectacled.modernbetaforge.api.client.gui.GuiPredicate;
import mod.bespectacled.modernbetaforge.api.registry.ModernBetaClientRegistries;
import mod.bespectacled.modernbetaforgeexamplemod.world.chunk.source.FlatChunkSource;

public class ModernBetaExampleModClientProxy implements ModernBetaExampleModProxy {
    @Override
    public void init() { 
        // Fetch GUI predicate for height setting and allow it to be used for FlatChunkSource
        ModernBetaClientRegistries.GUI_PREDICATE.get(GuiPredicate.HEIGHT).or(factory -> 
            factory.chunkSource.equals(FlatChunkSource.REGISTRY_KEY.toString())
        );
    }
}
