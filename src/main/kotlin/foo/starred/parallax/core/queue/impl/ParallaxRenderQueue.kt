package foo.starred.parallax.core.queue.impl

import foo.starred.parallax.Parallax.client
import foo.starred.parallax.api.events.ParallaxEvents
import foo.starred.parallax.core.data.impl.*
import foo.starred.parallax.core.queue.base.IParallaxRenderQueue
import foo.starred.parallax.core.queue.data.DualExtractedParallaxQueueElement
import foo.starred.parallax.core.queue.data.SingleExtractedParallaxQueueElement
import foo.starred.parallax.core.renderers.base.IParallaxRenderer
import foo.starred.parallax.core.renderers.impl.*
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents

object ParallaxRenderQueue : IParallaxRenderQueue {
    private val renderers: List<IParallaxRenderer> = listOf(ParallaxBoxRenderer, ParallaxCircleRenderer, ParallaxCylinderRenderer, ParallaxSphereRenderer, ParallaxLineRenderer, ParallaxBeamRenderer, ParallaxTextRenderer)

    override val beams: SingleExtractedParallaxQueueElement<ExtractedParallaxBeam> = SingleExtractedParallaxQueueElement(this)
    override val texts: SingleExtractedParallaxQueueElement<ExtractedParallaxText> = SingleExtractedParallaxQueueElement(this)
    override val lines: DualExtractedParallaxQueueElement<ExtractedParallaxLine> = DualExtractedParallaxQueueElement(this)
    override val boxes0: DualExtractedParallaxQueueElement<ExtractedParallaxBox> = DualExtractedParallaxQueueElement(this)
    override val boxes1: DualExtractedParallaxQueueElement<ExtractedParallaxBox> = DualExtractedParallaxQueueElement(this)
    override val circles0: DualExtractedParallaxQueueElement<ExtractedParallaxCircle> = DualExtractedParallaxQueueElement(this)
    override val circles1: DualExtractedParallaxQueueElement<ExtractedParallaxCircle> = DualExtractedParallaxQueueElement(this)
    override val cylinders0: DualExtractedParallaxQueueElement<ExtractedParallaxCylinder> = DualExtractedParallaxQueueElement(this)
    override val cylinders1: DualExtractedParallaxQueueElement<ExtractedParallaxCylinder> = DualExtractedParallaxQueueElement(this)
    override val spheres0: DualExtractedParallaxQueueElement<ExtractedParallaxSphere> = DualExtractedParallaxQueueElement(this)
    override val spheres1: DualExtractedParallaxQueueElement<ExtractedParallaxSphere> = DualExtractedParallaxQueueElement(this)

    override var empty: Boolean = true

    fun init() {
        LevelRenderEvents.START_MAIN.register {
            if (empty) return@register

            clear()
        }

        LevelRenderEvents.COLLECT_SUBMITS.register {
            ParallaxEvents.EXTRACT.invoker().extract()

            if (empty) return@register

            //~ if >= 26.2 'mainCamera' -> 'mainCamera()'
            val camera = client.gameRenderer.mainCamera.position()
            val poseStack = it.poseStack()
            val collector = it.submitNodeCollector()

            poseStack.pushPose()
            poseStack.translate(-camera.x, -camera.y, -camera.z)

            for (renderer in renderers) {
                renderer.render(poseStack, poseStack.last(), collector)
            }

            poseStack.popPose()
        }
    }

    override fun clear() {
        empty = true

        beams.clear()
        texts.clear()
        lines.clear()
        boxes0.clear()
        boxes1.clear()
        circles0.clear()
        circles1.clear()
        cylinders0.clear()
        cylinders1.clear()
        spheres0.clear()
        spheres1.clear()
    }
}
