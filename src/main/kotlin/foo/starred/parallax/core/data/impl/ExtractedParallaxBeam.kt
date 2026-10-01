package foo.starred.parallax.core.data.impl

import foo.starred.parallax.core.data.base.IParallaxExtractable
import net.minecraft.core.BlockPos

data class ExtractedParallaxBeam(
    val pos: BlockPos,
    val color: Int
) : IParallaxExtractable
