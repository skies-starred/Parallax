@file:Suppress("LocalVariableName")

package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import foo.starred.parallax.core.backend.rendertype.ParallaxRenderTypes
import foo.starred.parallax.core.data.impl.ExtractedParallaxCircle
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.world.phys.Vec3
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

object ParallaxCircleRenderer : IParallaxRenderer {
    override fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector) {
        forDepth(ParallaxRenderQueue.circles0) { depth, circles ->
            val renderType = if (depth) ParallaxRenderTypes.CIRCLE_OUTLINE.depth else ParallaxRenderTypes.CIRCLE_OUTLINE.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                quads(pose, buffer, circles)
            }
        }

        forDepth(ParallaxRenderQueue.circles1) { depth, circles ->
            val renderType = if (depth) ParallaxRenderTypes.CIRCLE_FILLED.depth else ParallaxRenderTypes.CIRCLE_FILLED.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                quads(pose, buffer, circles)
            }
        }
    }

    private fun quads(pose: PoseStack.Pose, buffer: VertexConsumer, circles: List<ExtractedParallaxCircle>) {
        for ((center, radius, color, width, normal) in circles) {
            val x0 = center.x.toFloat()
            val y0 = center.y.toFloat()
            val z0 = center.z.toFloat()
            val radius = radius.toFloat()
            val width = max(width, 0.5f)

            if (normal.x == 0.0 && normal.z == 0.0) {
                val i = if (normal.y >= 0.0) 1f else -1f

                buffer.addVertex(pose, x0 + radius * i, y0, z0 + radius * i).setUv(-1f, -width).setColor(color)
                buffer.addVertex(pose, x0 - radius * i, y0, z0 + radius * i).setUv(-1f, width).setColor(color)
                buffer.addVertex(pose, x0 - radius * i, y0, z0 - radius * i).setUv(1f, width).setColor(color)
                buffer.addVertex(pose, x0 + radius * i, y0, z0 - radius * i).setUv(1f, -width).setColor(color)

                continue
            }

            val (x1, y1, z1, x2, y2, z2) = fn(normal)
            fun vertex(u: Float, v: Float) {
                buffer.addVertex(pose, x0 + (x1 * u + x2 * v) * radius, y0 + (y1 * u + y2 * v) * radius, z0 + (z1 * u + z2 * v) * radius).setUv(u, if (v < 0f) -width else width).setColor(color)
            }

            vertex(-1f, -1f)
            vertex(-1f, 1f)
            vertex(1f, 1f)
            vertex(1f, -1f)
        }
    }

    private fun fn(normal: Vec3): FloatArray {
        val normal = normal.normalize()
        val x1 = if (abs(normal.x) < 0.9) 1.0 else 0.0
        val y1 = if (abs(normal.x) < 0.9) 0.0 else 1.0

        val _x0 = -normal.z * y1
        val _y0 = normal.z * x1
        val _z0 = normal.x * y1 - normal.y * x1
        val i = sqrt(_x0 * _x0 + _y0 * _y0 + _z0 * _z0)

        val _x1 = (_x0 / i).toFloat()
        val _y1 = (_y0 / i).toFloat()
        val _z1 = (_z0 / i).toFloat()

        val _x2 = (normal.y * _z1 - normal.z * _y1).toFloat()
        val _y2 = (normal.z * _x1 - normal.x * _z1).toFloat()
        val _z2 = (normal.x * _y1 - normal.y * _x1).toFloat()

        return floatArrayOf(_x1, _y1, _z1, _x2, _y2, _z2)
    }

    private operator fun FloatArray.component6(): Float {
        return this[5]
    }
}
