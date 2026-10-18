package foo.starred.parallax.core.queue.base

import foo.starred.parallax.core.data.impl.*
import foo.starred.parallax.core.queue.data.DualExtractedParallaxQueueElement
import foo.starred.parallax.core.queue.data.SingleExtractedParallaxQueueElement

interface IParallaxRenderQueue {
    val beams: SingleExtractedParallaxQueueElement<ExtractedParallaxBeam>
    val texts: SingleExtractedParallaxQueueElement<ExtractedParallaxText>
    val lines: DualExtractedParallaxQueueElement<ExtractedParallaxLine>
    val boxes0: DualExtractedParallaxQueueElement<ExtractedParallaxBox>
    val boxes1: DualExtractedParallaxQueueElement<ExtractedParallaxBox>
    val circles0: DualExtractedParallaxQueueElement<ExtractedParallaxCircle>
    val circles1: DualExtractedParallaxQueueElement<ExtractedParallaxCircle>
    val cylinders0: DualExtractedParallaxQueueElement<ExtractedParallaxCylinder>
    val cylinders1: DualExtractedParallaxQueueElement<ExtractedParallaxCylinder>
    val spheres0: DualExtractedParallaxQueueElement<ExtractedParallaxSphere>
    val spheres1: DualExtractedParallaxQueueElement<ExtractedParallaxSphere>

    var empty: Boolean

    fun clear()
}
