package foo.starred.parallax.core.renderers.impl

import com.mojang.blaze3d.vertex.PoseStack
import foo.starred.parallax.Parallax.client
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.blockentity.BeaconRenderer
import kotlin.math.sqrt

object ParallaxBeamRenderer : IParallaxRenderer {
    override fun render(poseStack: PoseStack, pose: PoseStack.Pose, collector: SubmitNodeCollector) {
        if (ParallaxRenderQueue.beams.empty()) return

        val level = client.level ?: return
        val player = client.player ?: return

        val time = level.gameTime % 40f + client.deltaTracker.getGameTimeDeltaPartialTick(false)
        val scoping = player.isScoping
        val x2 = player.x
        val z2 = player.z

        for ((pos, color) in ParallaxRenderQueue.beams) {
            val x1 = (pos.x + 0.5) - x2
            val z1 = (pos.z + 0.5) - z2
            val i0 = if (scoping) 1f else maxOf(1f, sqrt(x1 * x1 + z1 * z1).toFloat() / 96f)
            val radius0 = 0.2f * i0
            val radius1 = 0.25f * i0

            poseStack.pushPose()
            poseStack.translate(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())

            BeaconRenderer.submitBeaconBeam(poseStack, collector, BeaconRenderer.BEAM_LOCATION, 1.0f, time, 0, 320, color, radius0, radius1)

            poseStack.popPose()
        }
    }
}
