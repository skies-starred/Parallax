package foo.starred.parallax.core.backend.rendertype.base

import net.minecraft.client.renderer.rendertype.RenderType

interface IParallaxRenderType {
    val depth: RenderType
    val depthless: RenderType
}
