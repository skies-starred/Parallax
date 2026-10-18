package foo.starred.parallax.api.primitives

import foo.starred.parallax.core.data.impl.ExtractedParallaxCylinder
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.minecraft.world.phys.Vec3

object ParallaxCylinder {
    fun fill(bottom: Vec3, radius: Double, height: Double, color: Int, depth: Boolean = true) {
        ParallaxRenderQueue.cylinders1.add(ExtractedParallaxCylinder(bottom, radius, height, color, 1f), depth)
    }

    fun frame(bottom: Vec3, radius: Double, height: Double, color: Int, width: Float = 2f, depth: Boolean = true) {
        ParallaxRenderQueue.cylinders0.add(ExtractedParallaxCylinder(bottom, radius, height, color, width), depth)
    }
}
