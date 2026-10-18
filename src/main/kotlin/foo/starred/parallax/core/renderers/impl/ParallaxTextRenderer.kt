package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import foo.starred.parallax.Parallax.client
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.gui.Font
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.util.LightCoordsUtil

object ParallaxTextRenderer : IParallaxRenderer() {
    override fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector) {
        if (ParallaxRenderQueue.texts.empty()) return

        //~ if >= 26.2 'mainCamera' -> 'mainCamera()'
        val camera = client.gameRenderer.mainCamera
        val rotation = camera.rotation()
        val position = camera.position()

        for ((text, vec3, color, background, scale, shadow, depth, increase, factor) in ParallaxRenderQueue.texts) {
            poseStack.pushPose()

            val scale0 = if (increase) scale * position.distanceTo(vec3).toFloat() / factor else scale
            val scale1 = scale0 * 0.025f
            poseStack.translate(vec3.x, vec3.y, vec3.z)
            //~ if >= 26.3 'mulPose' -> 'rotate'
            poseStack.mulPose(rotation)
            poseStack.scale(scale1, -scale1, scale1)

            val x = -client.font.width(text) / 2f
            val mode = if (depth) Font.DisplayMode.POLYGON_OFFSET else Font.DisplayMode.SEE_THROUGH

            collector.submitText(poseStack, x, 0f, text, shadow, mode, LightCoordsUtil.FULL_BRIGHT, color, background, 0)
            poseStack.popPose()
        }
    }
}
