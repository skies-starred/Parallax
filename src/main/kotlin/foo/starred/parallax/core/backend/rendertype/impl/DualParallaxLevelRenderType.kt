package foo.starred.parallax.core.backend.rendertype.impl

import foo.starred.parallax.core.backend.pipeline.base.IParallaxPipeline
import foo.starred.parallax.core.backend.rendertype.base.IParallaxRenderType
import net.minecraft.client.renderer.rendertype.RenderSetup
import net.minecraft.client.renderer.rendertype.RenderType

class DualParallaxLevelRenderType(identifier: String, pipeline: IParallaxPipeline) : IParallaxRenderType {
    override val depth: RenderType =
        RenderType.create(
            "parallax_$identifier",
            RenderSetup.builder(pipeline.depth)
                //? if <= 26.1
                .bufferSize(RenderType.TRANSIENT_BUFFER_SIZE)
                .createRenderSetup()
        )

    override val depthless: RenderType =
        RenderType.create(
            "parallax_${identifier}_depthless",
            RenderSetup.builder(pipeline.depthless)
                //? if <= 26.1
                .bufferSize(RenderType.TRANSIENT_BUFFER_SIZE)
                .createRenderSetup()
        )
}
