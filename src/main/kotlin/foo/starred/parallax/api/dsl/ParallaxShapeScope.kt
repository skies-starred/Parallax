@file:Suppress("NOTHING_TO_INLINE", "unused")

package foo.starred.parallax.api.dsl

import foo.starred.parallax.api.primitives.*
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.util.FormattedCharSequence
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f

@JvmInline
value class ParallaxShapeScope @PublishedApi internal constructor(@PublishedApi internal val unused: Boolean) {
    inline fun filledBox(aabb: AABB, color: Int, depth: Boolean = true) {
        ParallaxBox.fill(aabb, color, depth)
    }

    inline fun frameBox(aabb: AABB, color: Int, width: Float = 2f, depth: Boolean = true) {
        ParallaxBox.frame(aabb, color, width, depth)
    }

    inline fun filledCircle(center: Vec3, radius: Double, color: Int, normal: Vec3 = ParallaxCircle.NORMAL, depth: Boolean = true) {
        ParallaxCircle.fill(center, radius, color, normal, depth)
    }

    inline fun frameCircle(center: Vec3, radius: Double, color: Int, normal: Vec3 = ParallaxCircle.NORMAL, width: Float = 2f, depth: Boolean = true) {
        ParallaxCircle.frame(center, radius, color, normal, width, depth)
    }

    inline fun line(start: Vector3f, end: Vector3f, color: Int, width: Float = 2f, depth: Boolean = true) {
        ParallaxLine.singular(start, end, color, width, depth)
    }

    inline fun lines(points: List<Vector3f>, color: Int, width: Float = 2f, depth: Boolean = true) {
        ParallaxLine.multiple(points, color, width, depth)
    }

    inline fun beam(pos: BlockPos, color: Int) {
        ParallaxBeam.extract(pos, color)
    }

    inline fun formatted(text: FormattedCharSequence, pos: Vec3, color: Int = -1, background: Int = 0, scale: Float = 1f, depth: Boolean = false, shadow: Boolean = true, increase: Boolean = false, factor: Float = 3f) {
        ParallaxText.formatted(text, pos, color, background, scale, depth, shadow, increase, factor)
    }

    inline fun component(text: Component, pos: Vec3, color: Int = -1, background: Int = 0, scale: Float = 1f, depth: Boolean = false, shadow: Boolean = true, increase: Boolean = false, factor: Float = 3f) {
        ParallaxText.component(text, pos, color, background, scale, depth, shadow, increase, factor)
    }

    inline fun string(text: String, pos: Vec3, color: Int = -1, background: Int = 0, scale: Float = 1f, depth: Boolean = false, shadow: Boolean = true, increase: Boolean = false, factor: Float = 3f) {
        ParallaxText.string(text, pos, color, background, scale, depth, shadow, increase, factor)
    }
}
