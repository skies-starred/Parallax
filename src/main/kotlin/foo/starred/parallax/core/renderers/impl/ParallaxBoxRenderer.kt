@file:Suppress("LocalVariableName")

package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import foo.starred.parallax.core.backend.rendertype.ParallaxRenderTypes
import foo.starred.parallax.core.data.impl.ExtractedParallaxBox
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.renderer.SubmitNodeCollector

object ParallaxBoxRenderer : IParallaxRenderer {
    private val edges = arrayOf(
        floatArrayOf(0f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 0f),
        floatArrayOf(1f, 0f, 0f, 1f, 0f, 1f, 0f, 0f, 1f),
        floatArrayOf(1f, 0f, 1f, 0f, 0f, 1f, -1f, 0f, 0f),
        floatArrayOf(0f, 0f, 1f, 0f, 0f, 0f, 0f, 0f, -1f),
        floatArrayOf(0f, 1f, 0f, 1f, 1f, 0f, 1f, 0f, 0f),
        floatArrayOf(1f, 1f, 0f, 1f, 1f, 1f, 0f, 0f, 1f),
        floatArrayOf(1f, 1f, 1f, 0f, 1f, 1f, -1f, 0f, 0f),
        floatArrayOf(0f, 1f, 1f, 0f, 1f, 0f, 0f, 0f, -1f),
        floatArrayOf(0f, 0f, 0f, 0f, 1f, 0f, 0f, 1f, 0f),
        floatArrayOf(1f, 0f, 0f, 1f, 1f, 0f, 0f, 1f, 0f),
        floatArrayOf(1f, 0f, 1f, 1f, 1f, 1f, 0f, 1f, 0f),
        floatArrayOf(0f, 0f, 1f, 0f, 1f, 1f, 0f, 1f, 0f)
    )

    override fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector) {
        forDepth(ParallaxRenderQueue.boxes0) { depth, boxes ->
            val renderType = if (depth) ParallaxRenderTypes.LINES.depth else ParallaxRenderTypes.LINES.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                outline(pose, buffer, boxes)
            }
        }

        forDepth(ParallaxRenderQueue.boxes1) { depth, boxes ->
            val renderType = if (depth) ParallaxRenderTypes.DEBUG_FILLED.depth else ParallaxRenderTypes.DEBUG_FILLED.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                filled(pose, buffer, boxes)
            }
        }
    }

    private fun outline(pose: PoseStack.Pose, buffer: VertexConsumer, boxes: List<ExtractedParallaxBox>) {
        for ((aabb, color, width) in boxes) {
            val x0 = aabb.minX.toFloat()
            val x1 = aabb.maxX.toFloat()
            val y0 = aabb.minY.toFloat()
            val y1 = aabb.maxY.toFloat()
            val z0 = aabb.minZ.toFloat()
            val z1 = aabb.maxZ.toFloat()

            for (edge in edges) {
                val _x1 = if (edge[0] == 0f) x0 else x1
                val _y1 = if (edge[1] == 0f) y0 else y1
                val _z1 = if (edge[2] == 0f) z0 else z1
                val _x2 = if (edge[3] == 0f) x0 else x1
                val _y2 = if (edge[4] == 0f) y0 else y1
                val _z2 = if (edge[5] == 0f) z0 else z1

                buffer.addVertex(pose, _x1, _y1, _z1).setColor(color).setNormal(pose, edge[6], edge[7], edge[8]).setLineWidth(width)
                buffer.addVertex(pose, _x2, _y2, _z2).setColor(color).setNormal(pose, edge[6], edge[7], edge[8]).setLineWidth(width)
            }
        }
    }

    private fun filled(pose: PoseStack.Pose, buffer: VertexConsumer, boxes: List<ExtractedParallaxBox>) {
        for ((aabb, color) in boxes) {
            val x0 = aabb.minX.toFloat()
            val x1 = aabb.maxX.toFloat()
            val y0 = aabb.minY.toFloat()
            val y1 = aabb.maxY.toFloat()
            val z0 = aabb.minZ.toFloat()
            val z1 = aabb.maxZ.toFloat()

            buffer.addVertex(pose, x0, y0, z0).setColor(color)
            buffer.addVertex(pose, x0, y0, z1).setColor(color)
            buffer.addVertex(pose, x0, y1, z1).setColor(color)
            buffer.addVertex(pose, x0, y1, z0).setColor(color)

            buffer.addVertex(pose, x1, y0, z1).setColor(color)
            buffer.addVertex(pose, x1, y0, z0).setColor(color)
            buffer.addVertex(pose, x1, y1, z0).setColor(color)
            buffer.addVertex(pose, x1, y1, z1).setColor(color)

            buffer.addVertex(pose, x0, y0, z0).setColor(color)
            buffer.addVertex(pose, x1, y0, z0).setColor(color)
            buffer.addVertex(pose, x1, y1, z0).setColor(color)
            buffer.addVertex(pose, x0, y1, z0).setColor(color)

            buffer.addVertex(pose, x1, y0, z1).setColor(color)
            buffer.addVertex(pose, x0, y0, z1).setColor(color)
            buffer.addVertex(pose, x0, y1, z1).setColor(color)
            buffer.addVertex(pose, x1, y1, z1).setColor(color)

            buffer.addVertex(pose, x0, y0, z0).setColor(color)
            buffer.addVertex(pose, x1, y0, z0).setColor(color)
            buffer.addVertex(pose, x1, y0, z1).setColor(color)
            buffer.addVertex(pose, x0, y0, z1).setColor(color)

            buffer.addVertex(pose, x0, y1, z0).setColor(color)
            buffer.addVertex(pose, x0, y1, z1).setColor(color)
            buffer.addVertex(pose, x1, y1, z1).setColor(color)
            buffer.addVertex(pose, x1, y1, z0).setColor(color)
        }
    }
}
