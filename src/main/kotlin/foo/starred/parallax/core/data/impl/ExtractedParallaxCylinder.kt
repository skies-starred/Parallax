package foo.starred.parallax.core.data.impl

import foo.starred.parallax.core.data.base.IParallaxExtractable
import net.minecraft.world.phys.Vec3

data class ExtractedParallaxCylinder(
    val bottom: Vec3,
    val radius: Double,
    val height: Double,
    val color: Int,
    val width: Float
) : IParallaxExtractable
