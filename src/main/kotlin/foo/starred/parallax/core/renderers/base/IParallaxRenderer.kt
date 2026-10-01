package foo.starred.parallax.core.renderers.base

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import foo.starred.parallax.core.data.base.IParallaxExtractable
import foo.starred.parallax.core.queue.data.DualExtractedParallaxQueueElement
import net.minecraft.client.renderer.SubmitNodeCollector
import kotlin.math.sqrt

interface IParallaxRenderer {
    fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector)

    fun <T : IParallaxExtractable> forDepth(list: DualExtractedParallaxQueueElement<T>, block: (depth: Boolean, list: List<T>) -> Unit) {
        if (list.depth.isNotEmpty()) {
            block(true, list.depth)
        }

        if (list.depthless.isNotEmpty()) {
            block(false, list.depthless)
        }
    }

    fun vertex(pose: PoseStack.Pose, buffer: VertexConsumer, x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float, width: Float, color: Int) {
        val x3 = x2 - x1
        val y3 = y2 - y1
        val z3 = z2 - z1
        val length = sqrt(x3 * x3 + y3 * y3 + z3 * z3)

        val x4 = if (length > 0) x3 / length else 0f
        val y4 = if (length > 0) y3 / length else 0f
        val z4 = if (length > 0) z3 / length else 0f

        buffer.addVertex(pose, x1, y1, z1).setColor(color).setNormal(pose, x4, y4, z4).setLineWidth(width)
        buffer.addVertex(pose, x2, y2, z2).setColor(color).setNormal(pose, x4, y4, z4).setLineWidth(width)
    }
}
