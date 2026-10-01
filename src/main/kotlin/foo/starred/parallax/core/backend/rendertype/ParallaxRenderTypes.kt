package foo.starred.parallax.core.backend.rendertype

import foo.starred.parallax.core.backend.pipeline.ParallaxPipelines
import foo.starred.parallax.core.backend.rendertype.impl.DualParallaxLevelRenderType

object ParallaxRenderTypes {
    val LINES = DualParallaxLevelRenderType("lines", ParallaxPipelines.LINES)
    val DEBUG_FILLED = DualParallaxLevelRenderType("debug_filled", ParallaxPipelines.DEBUG_FILLED)
    val CIRCLE_FILLED = DualParallaxLevelRenderType("sdf_circle_filled", ParallaxPipelines.CIRCLE_FILLED)
    val CIRCLE_OUTLINE = DualParallaxLevelRenderType("sdf_circle_outline", ParallaxPipelines.CIRCLE_OUTLINE)
}
