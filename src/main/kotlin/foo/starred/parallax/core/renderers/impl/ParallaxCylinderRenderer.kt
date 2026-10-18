package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import foo.starred.parallax.core.backend.rendertype.ParallaxRenderTypes
import foo.starred.parallax.core.data.impl.ExtractedParallaxCylinder
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.renderer.SubmitNodeCollector
import kotlin.math.max

object ParallaxCylinderRenderer : IParallaxRenderer() {
    override fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector) {
        forDepth(ParallaxRenderQueue.cylinders0) { depth, cylinders ->
            val renderType0 = if (depth) ParallaxRenderTypes.CIRCLE_OUTLINE.depth else ParallaxRenderTypes.CIRCLE_OUTLINE.depthless
            val renderType1 = if (depth) ParallaxRenderTypes.LINES.depth else ParallaxRenderTypes.LINES.depthless

            collector.submitCustomGeometry(poseStack, renderType0) { pose, buffer ->
                circles(pose, buffer, cylinders)
            }

            collector.submitCustomGeometry(poseStack, renderType1) { pose, buffer ->
                lines(pose, buffer, cylinders)
            }
        }

        forDepth(ParallaxRenderQueue.cylinders1) { depth, cylinders ->
            val renderType = if (depth) ParallaxRenderTypes.CYLINDER_FILLED.depth else ParallaxRenderTypes.CYLINDER_FILLED.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                boxes(pose, buffer, cylinders)
            }
        }
    }

    private fun circles(pose: PoseStack.Pose, buffer: VertexConsumer, cylinders: List<ExtractedParallaxCylinder>) {
        for ((bottom, radius, height, color, width) in cylinders) {
            val x0 = bottom.x.toFloat()
            val y1 = bottom.y.toFloat()
            val y2 = (bottom.y + height).toFloat()
            val z0 = bottom.z.toFloat()
            val radius = radius.toFloat()
            val width = max(width, 0.5f)

            circle(pose, buffer, x0, y1, z0, radius, width, color)
            circle(pose, buffer, x0, y2, z0, radius, width, color)
        }
    }

    private fun circle(pose: PoseStack.Pose, buffer: VertexConsumer, x0: Float, y0: Float, z0: Float, radius: Float, width: Float, color: Int) {
        buffer.addVertex(pose, x0 + radius, y0, z0 + radius).setUv(-1f, -width).setColor(color)
        buffer.addVertex(pose, x0 - radius, y0, z0 + radius).setUv(-1f, width).setColor(color)
        buffer.addVertex(pose, x0 - radius, y0, z0 - radius).setUv(1f, width).setColor(color)
        buffer.addVertex(pose, x0 + radius, y0, z0 - radius).setUv(1f, -width).setColor(color)
    }

    private fun lines(pose: PoseStack.Pose, buffer: VertexConsumer, cylinders: List<ExtractedParallaxCylinder>) {
        for ((bottom, radius, height, color, width) in cylinders) {
            val x0 = bottom.x.toFloat()
            val y1 = bottom.y.toFloat()
            val y2 = (bottom.y + height).toFloat()
            val z0 = bottom.z.toFloat()
            val radius = radius.toFloat()
            val width = max(width, 0.5f)

            vertex(pose, buffer, x0 + radius, y1, z0, x0 + radius, y2, z0, width, color)
            vertex(pose, buffer, x0 - radius, y1, z0, x0 - radius, y2, z0, width, color)
            vertex(pose, buffer, x0, y1, z0 + radius, x0, y2, z0 + radius, width, color)
            vertex(pose, buffer, x0, y1, z0 - radius, x0, y2, z0 - radius, width, color)
        }
    }

    private fun boxes(pose: PoseStack.Pose, buffer: VertexConsumer, cylinders: List<ExtractedParallaxCylinder>) {
        for ((bottom, radius, height, color, width) in cylinders) {
            val radius = radius.toFloat()
            val height = height.toFloat()

            val x1 = (bottom.x - radius).toFloat()
            val x2 = (bottom.x + radius).toFloat()
            val y1 = bottom.y.toFloat()
            val y2 = (bottom.y + height).toFloat()
            val z1 = (bottom.z - radius).toFloat()
            val z2 = (bottom.z + radius).toFloat()

            val v = (height * 0.5f) + width * 1000f
            box(pose, buffer, x1, y1, z1, x2, y2, z2, radius, v, color)
        }
    }
}
