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

    val CIRCLE_FILLED = DualParallaxLevelPipeline("circle_filled") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        vertexShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle")
        fragmentShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle")
        vertexFormat = DefaultVertexFormat.POSITION_TEX_COLOR
        cull = false
    }

    val CIRCLE_OUTLINE = DualParallaxLevelPipeline("circle_outline") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        vertexShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle")
        fragmentShader = Identifier.fromNamespaceAndPath("parallax", "core/circle/circle_outline")
        vertexFormat = DefaultVertexFormat.POSITION_TEX_COLOR
        cull = false
    }

    val CYLINDER_FILLED = DualParallaxLevelPipeline("cylinder_filled") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        vertexShader = Identifier.fromNamespaceAndPath("parallax", "core/cylinder/cylinder")
        fragmentShader = Identifier.fromNamespaceAndPath("parallax", "core/cylinder/cylinder")
        vertexFormat = DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL
        cull = false
    }

    val SPHERE_FILLED = DualParallaxLevelPipeline("sphere_filled") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        vertexShader = Identifier.fromNamespaceAndPath("parallax", "core/sphere/sphere")
        fragmentShader = Identifier.fromNamespaceAndPath("parallax", "core/sphere/sphere")
        vertexFormat = DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL
        cull = false
    }

    val SPHERE_OUTLINE = DualParallaxLevelPipeline("sphere_outline") {
        snippet = RenderPipelines.DEBUG_FILLED_SNIPPET
        vertexShader = Identifier.fromNamespaceAndPath("parallax", "core/sphere/sphere")
        fragmentShader = Identifier.fromNamespaceAndPath("parallax", "core/sphere/sphere_outline")
        vertexFormat = DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL
        cull = false
    }
}
