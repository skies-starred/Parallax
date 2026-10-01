package foo.starred.parallax.core.data.impl

import foo.starred.parallax.core.data.base.IParallaxExtractable
import org.joml.Vector3f

data class ExtractedParallaxLine(
    val start: Vector3f,
    val end: Vector3f,
    val color: Int,
    val width: Float
) : IParallaxExtractable
