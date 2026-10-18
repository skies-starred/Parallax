package foo.starred.parallax.api.primitives

import foo.starred.parallax.core.data.impl.ExtractedParallaxSphere
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.minecraft.world.phys.Vec3

object ParallaxSphere {
    fun fill(center: Vec3, radius: Double, color: Int, depth: Boolean = true) {
        ParallaxRenderQueue.spheres1.add(ExtractedParallaxSphere(center, radius, color, 1f), depth)
    }

    fun frame(center: Vec3, radius: Double, color: Int, width: Float = 2f, depth: Boolean = true) {
        ParallaxRenderQueue.spheres0.add(ExtractedParallaxSphere(center, radius, color, width), depth)
    }
}
