package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import foo.starred.parallax.core.backend.rendertype.ParallaxRenderTypes
import foo.starred.parallax.core.data.impl.ExtractedParallaxLine
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.renderer.SubmitNodeCollector

object ParallaxLineRenderer : IParallaxRenderer {
    override fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector) {
        forDepth(ParallaxRenderQueue.lines) { depth, lines ->
            val renderType = if (depth) ParallaxRenderTypes.LINES.depth else ParallaxRenderTypes.LINES.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                extract(pose, buffer, lines)
            }
        }
    }

    private fun extract(pose: PoseStack.Pose, buffer: VertexConsumer, lines: List<ExtractedParallaxLine>) {
        for ((start, end, color, width) in lines) {
            vertex(pose, buffer, start.x, start.y, start.z, end.x, end.y, end.z, width, color)
        }
    }
}
