package mod.bespectacled.modernbetaexample;

import mod.bespectacled.modernbetaexample.world.chunk.source.FlatChunkSource;
import mod.bespectacled.modernbetaforge.api.client.gui.GuiPredicate;
import mod.bespectacled.modernbetaforge.api.registry.ModernBetaClientRegistries;

public class ModernBetaExampleModClientProxy implements ModernBetaExampleModProxy {
    @Override
    public void init() { 
        // Fetch GUI predicate for height setting and allow it to be used for FlatChunkSource
        ModernBetaClientRegistries.GUI_PREDICATE.get(GuiPredicate.HEIGHT).or(settings -> 
            settings.chunkSource.equals(FlatChunkSource.REGISTRY_KEY.toString())
        );
    }
}
