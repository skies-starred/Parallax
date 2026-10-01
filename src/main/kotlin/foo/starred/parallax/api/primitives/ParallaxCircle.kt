package foo.starred.parallax.api.primitives

import foo.starred.parallax.core.data.impl.ExtractedParallaxCircle
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.minecraft.world.phys.Vec3

object ParallaxCircle {
    val NORMAL = Vec3(0.0, 1.0, 0.0)

    fun fill(center: Vec3, radius: Double, color: Int, normal: Vec3 = NORMAL, depth: Boolean = true) {
        ParallaxRenderQueue.circles1.add(ExtractedParallaxCircle(center, radius, color, 1f, normal), depth)
    }

    fun frame(center: Vec3, radius: Double, color: Int, normal: Vec3 = NORMAL, width: Float = 2f, depth: Boolean = true) {
        ParallaxRenderQueue.circles0.add(ExtractedParallaxCircle(center, radius, color, width, normal), depth)
    }
}
