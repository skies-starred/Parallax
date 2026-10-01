package foo.starred.parallax.core.data.impl

import foo.starred.parallax.core.data.base.IParallaxExtractable
import net.minecraft.world.phys.AABB

data class ExtractedParallaxBox(
    val aabb: AABB,
    val color: Int,
    val width: Float
) : IParallaxExtractable
