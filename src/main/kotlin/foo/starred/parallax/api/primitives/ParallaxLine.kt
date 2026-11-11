package foo.starred.parallax.api.primitives

import foo.starred.parallax.Parallax.client
import foo.starred.parallax.core.data.impl.ExtractedParallaxLine
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.minecraft.world.phys.Vec3
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

    fun tracer(end: Vector3f, color: Int, width: Float = 3f, depth: Boolean = true) {
        //~ if >= 26.2 'client.gameRenderer.mainCamera' -> 'client.gameRenderer.mainCamera()'
        val camera = client.gameRenderer.mainCamera
        val start = camera.position().add(Vec3.directionFromRotation(camera.xRot(), camera.yRot())).toVector3f()

        singular(start, end, color, width, depth)
    }
}
