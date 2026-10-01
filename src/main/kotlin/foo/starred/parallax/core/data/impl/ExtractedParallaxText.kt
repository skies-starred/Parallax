package foo.starred.parallax.core.data.impl

import foo.starred.parallax.core.data.base.IParallaxExtractable
import net.minecraft.util.FormattedCharSequence
import net.minecraft.world.phys.Vec3

data class ExtractedParallaxText(
    val text: FormattedCharSequence,
    val vec3: Vec3,
    val color: Int,
    val background: Int,
    val scale: Float,
    val shadow: Boolean,
    val depth: Boolean,
    val increase: Boolean,
    val factor: Float
) : IParallaxExtractable
