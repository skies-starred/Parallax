package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import foo.starred.parallax.core.backend.rendertype.ParallaxRenderTypes
import foo.starred.parallax.core.data.impl.ExtractedParallaxBox
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.renderer.SubmitNodeCollector

object ParallaxBoxRenderer : IParallaxRenderer() {
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
            val x1 = aabb.minX.toFloat()
            val x2 = aabb.maxX.toFloat()
            val y1 = aabb.minY.toFloat()
            val y2 = aabb.maxY.toFloat()
            val z1 = aabb.minZ.toFloat()
            val z2 = aabb.maxZ.toFloat()

            box(pose, buffer, x1, y1, z1, x2, y2, z2, color, width)
        }
    }

    private fun filled(pose: PoseStack.Pose, buffer: VertexConsumer, boxes: List<ExtractedParallaxBox>) {
        for ((aabb, color) in boxes) {
            val x1 = aabb.minX.toFloat()
            val x2 = aabb.maxX.toFloat()
            val y1 = aabb.minY.toFloat()
            val y2 = aabb.maxY.toFloat()
            val z1 = aabb.minZ.toFloat()
            val z2 = aabb.maxZ.toFloat()

            box(pose, buffer, x1, y1, z1, x2, y2, z2, color)
        }
    }
}
