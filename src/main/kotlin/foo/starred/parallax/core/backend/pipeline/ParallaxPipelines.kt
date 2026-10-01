package foo.starred.parallax.core.backend.pipeline

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import foo.starred.parallax.core.backend.pipeline.impl.DualParallaxLevelPipeline
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier

object ParallaxPipelines {
    val LINES = DualParallaxLevelPipeline("line") {
        snippet = RenderPipelines.LINES_SNIPPET
    }

    val DEBUG_FILLED = DualParallaxLevelPipeline("debug_filled") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        cull = false
    }

    val CIRCLE_FILLED = DualParallaxLevelPipeline("sdf_circle_filled") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        vertexShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle")
        fragmentShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle")
        vertexFormat = DefaultVertexFormat.POSITION_TEX_COLOR
        cull = false
    }

    val CIRCLE_OUTLINE = DualParallaxLevelPipeline("sdf_circle_outline") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        vertexShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle")
        fragmentShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle_outline")
        vertexFormat = DefaultVertexFormat.POSITION_TEX_COLOR
        cull = false
    }
}
