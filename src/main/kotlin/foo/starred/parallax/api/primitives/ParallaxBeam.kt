package foo.starred.parallax.api.primitives

import foo.starred.parallax.core.data.impl.ExtractedParallaxBeam
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.minecraft.core.BlockPos

object ParallaxBeam {
    fun extract(pos: BlockPos, color: Int) {
        ParallaxRenderQueue.beams.add(ExtractedParallaxBeam(pos, color))
    }
}
