package foo.starred.parallax.core.queue.data

import foo.starred.parallax.core.data.base.IParallaxExtractable
import foo.starred.parallax.core.queue.base.IParallaxQueueElement
import foo.starred.parallax.core.queue.base.IParallaxRenderQueue

class DualExtractedParallaxQueueElement<T : IParallaxExtractable>(val parent: IParallaxRenderQueue) : IParallaxQueueElement {
    val depth = mutableListOf<T>()
    val depthless = mutableListOf<T>()

    fun add(entry: T, depth: Boolean) {
        parent.empty = false
        (if (depth) this.depth else depthless).add(entry)
    }

    override fun clear() {
        depth.clear()
        depthless.clear()
    }

    override fun empty(): Boolean {
        return depth.isEmpty() && depthless.isEmpty()
    }
}
