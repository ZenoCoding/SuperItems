package net.minecraft.world.level.levelgen.carver;

import com.mojang.serialization.Codec;
import java.util.function.Function;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.IRegistry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkCoordIntPair;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.IChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;

public record WorldGenCarverWrapper<WC extends WorldGenCarverConfiguration> (WorldGenCarverAbstract<WC> worldCarver, WC config) {

    public static final Codec<WorldGenCarverWrapper<?>> DIRECT_CODEC = IRegistry.CARVER.byNameCodec().dispatch((worldgencarverwrapper) -> {
        return worldgencarverwrapper.worldCarver;
    }, WorldGenCarverAbstract::configuredCodec);
    public static final Codec<Holder<WorldGenCarverWrapper<?>>> CODEC = RegistryFileCodec.create(IRegistry.CONFIGURED_CARVER_REGISTRY, WorldGenCarverWrapper.DIRECT_CODEC);
    public static final Codec<HolderSet<WorldGenCarverWrapper<?>>> LIST_CODEC = RegistryCodecs.homogeneousList(IRegistry.CONFIGURED_CARVER_REGISTRY, WorldGenCarverWrapper.DIRECT_CODEC);

    public boolean isStartChunk(RandomSource randomsource) {
        return this.worldCarver.isStartChunk(this.config, randomsource);
    }

    public boolean carve(CarvingContext carvingcontext, IChunkAccess ichunkaccess, Function<BlockPosition, Holder<BiomeBase>> function, RandomSource randomsource, Aquifer aquifer, ChunkCoordIntPair chunkcoordintpair, CarvingMask carvingmask) {
        return SharedConstants.debugVoidTerrain(ichunkaccess.getPos()) ? false : this.worldCarver.carve(carvingcontext, this.config, ichunkaccess, function, randomsource, aquifer, chunkcoordintpair, carvingmask);
    }
}
