package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import foo.starred.parallax.core.backend.rendertype.ParallaxRenderTypes
import foo.starred.parallax.core.data.impl.ExtractedParallaxSphere
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.renderer.SubmitNodeCollector

object ParallaxSphereRenderer : IParallaxRenderer() {
    override fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector) {
        forDepth(ParallaxRenderQueue.spheres0) { depth, spheres ->
            val renderType = if (depth) ParallaxRenderTypes.SPHERE_OUTLINE.depth else ParallaxRenderTypes.SPHERE_OUTLINE.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                cubes(pose, buffer, spheres)
            }
        }

        forDepth(ParallaxRenderQueue.spheres1) { depth, spheres ->
            val renderType = if (depth) ParallaxRenderTypes.SPHERE_FILLED.depth else ParallaxRenderTypes.SPHERE_FILLED.depthless

            collector.submitCustomGeometry(poseStack, renderType) { pose, buffer ->
                cubes(pose, buffer, spheres)
            }
        }
    }

    private fun cubes(pose: PoseStack.Pose, buffer: VertexConsumer, spheres: List<ExtractedParallaxSphere>) {
        for ((center, radius, color, width) in spheres) {
            val radius = radius.toFloat()

            val x1 = (center.x - radius).toFloat()
            val x2 = (center.x + radius).toFloat()
            val y1 = (center.y - radius).toFloat()
            val y2 = (center.y + radius).toFloat()
            val z1 = (center.z - radius).toFloat()
            val z2 = (center.z + radius).toFloat()

            box(pose, buffer, x1, y1, z1, x2, y2, z2, radius, width, color)
        }
    }
}
