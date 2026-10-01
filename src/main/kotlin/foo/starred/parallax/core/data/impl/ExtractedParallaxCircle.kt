package foo.starred.parallax.core.data.impl

import foo.starred.parallax.core.data.base.IParallaxExtractable
import net.minecraft.world.phys.Vec3

data class ExtractedParallaxCircle(
    val center: Vec3,
    val radius: Double,
    val color: Int,
    val width: Float,
    val normal: Vec3
) : IParallaxExtractable
