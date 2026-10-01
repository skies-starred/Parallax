package foo.starred.parallax.core.queue.data

import foo.starred.parallax.core.data.base.IParallaxExtractable
import foo.starred.parallax.core.queue.base.IParallaxQueueElement
import foo.starred.parallax.core.queue.base.IParallaxRenderQueue

class SingleExtractedParallaxQueueElement<T : IParallaxExtractable>(val parent: IParallaxRenderQueue) : IParallaxQueueElement, Iterable<T> {
    val entries: MutableList<T> = mutableListOf()

    fun add(entry: T) {
        parent.empty = false
        entries.add(entry)
    }

    override fun clear() {
        entries.clear()
    }

    override fun empty(): Boolean {
        return entries.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return entries.iterator()
    }
}
