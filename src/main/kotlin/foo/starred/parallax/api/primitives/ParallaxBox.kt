package foo.starred.parallax.api.primitives

import foo.starred.parallax.core.data.impl.ExtractedParallaxBox
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.minecraft.world.phys.AABB

object ParallaxBox {
    fun fill(aabb: AABB, color: Int, depth: Boolean = true) {
        ParallaxRenderQueue.boxes1.add(ExtractedParallaxBox(aabb, color, 1f), depth)
    }

    fun frame(aabb: AABB, color: Int, width: Float = 2f, depth: Boolean = true) {
        ParallaxRenderQueue.boxes0.add(ExtractedParallaxBox(aabb, color, width), depth)
    }
}
