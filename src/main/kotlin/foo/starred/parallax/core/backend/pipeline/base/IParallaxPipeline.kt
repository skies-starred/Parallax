package foo.starred.parallax.core.backend.pipeline.base

import com.mojang.blaze3d.pipeline.RenderPipeline

interface IParallaxPipeline {
    val depth: RenderPipeline
    val depthless: RenderPipeline
}
