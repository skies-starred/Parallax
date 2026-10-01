package foo.starred.parallax.core.backend.pipeline.impl

import com.mojang.blaze3d.pipeline.RenderPipeline
import foo.starred.parallax.core.backend.pipeline.base.IParallaxPipeline
import foo.starred.parallax.core.backend.pipeline.builder.ParallaxPipelineBuilder
import foo.starred.parallax.core.backend.pipeline.depth.ParallaxPipelineDepth
import net.minecraft.client.renderer.RenderPipelines

class DualParallaxLevelPipeline(identifier: String, block: ParallaxPipelineBuilder.() -> Unit) : IParallaxPipeline {
    override val depth: RenderPipeline = RenderPipelines.register(
        ParallaxPipelineBuilder().apply(block).apply {
            location = "depth/$identifier"
        }.build()
    )

    override val depthless: RenderPipeline = RenderPipelines.register(
        ParallaxPipelineBuilder().apply(block).apply {
            location = "depthless/$identifier"
            depth = ParallaxPipelineDepth.NONE
        }.build()
    )
}
