package foo.starred.parallax.core.backend.rendertype

import foo.starred.parallax.core.backend.pipeline.ParallaxPipelines
import foo.starred.parallax.core.backend.rendertype.impl.DualParallaxLevelRenderType

object ParallaxRenderTypes {
    val LINES = DualParallaxLevelRenderType("lines", ParallaxPipelines.LINES)
    val DEBUG_FILLED = DualParallaxLevelRenderType("debug_filled", ParallaxPipelines.DEBUG_FILLED)

    val CIRCLE_FILLED = DualParallaxLevelRenderType("circle_filled", ParallaxPipelines.CIRCLE_FILLED)
    val CIRCLE_OUTLINE = DualParallaxLevelRenderType("circle_outline", ParallaxPipelines.CIRCLE_OUTLINE)

    val CYLINDER_FILLED = DualParallaxLevelRenderType("cylinder_filled", ParallaxPipelines.CYLINDER_FILLED)

    val SPHERE_FILLED = DualParallaxLevelRenderType("sphere_filled", ParallaxPipelines.SPHERE_FILLED)
    val SPHERE_OUTLINE = DualParallaxLevelRenderType("sphere_outline", ParallaxPipelines.SPHERE_OUTLINE)
}
