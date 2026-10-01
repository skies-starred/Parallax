package foo.starred.parallax.api.primitives

import foo.starred.parallax.core.data.impl.ExtractedParallaxLine
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import org.joml.Vector3f

object ParallaxLine {
    fun singular(start: Vector3f, end: Vector3f, color: Int, width: Float = 2f, depth: Boolean = true) {
        ParallaxRenderQueue.lines.add(ExtractedParallaxLine(start, end, color, width), depth)
    }

    fun multiple(points: List<Vector3f>, color: Int, width: Float = 2f, depth: Boolean = true) {
        for (i in 0 until points.size - 1) {
            singular(points[i], points[i + 1], color, width, depth)
        }
    }
}
