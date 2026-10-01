package foo.starred.parallax.core.backend.pipeline.builder

import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.vertex.VertexFormat
import foo.starred.parallax.core.backend.pipeline.depth.ParallaxPipelineDepth
import net.minecraft.resources.Identifier
import kotlin.jvm.optionals.getOrNull

//? if >= 26.3 {
/*import com.mojang.renderpearl.api.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.ColorTargetState
import com.mojang.renderpearl.api.pipeline.PrimitiveTopology
*///?} elif 26.2 {
/*import com.mojang.blaze3d.PrimitiveTopology
*///?}

class ParallaxPipelineBuilder {
    lateinit var snippet: RenderPipeline.Snippet
    lateinit var location: String

    var depth: ParallaxPipelineDepth = ParallaxPipelineDepth.LEQUAL
    var cull: Boolean = true

    var vertexFormat: VertexFormat? = null
    //~ if >= 26.2 'VertexFormat.Mode' -> 'PrimitiveTopology'
    var vertexMode: VertexFormat.Mode? = null
    //? if >= 26.3
    //var colorTargetState: ColorTargetState? = null

    var vertexShader: Identifier? = null
    var fragmentShader: Identifier? = null

    fun build(): RenderPipeline {
        if (vertexFormat == null) {
            //~ if >= 26.2 'snippet.vertexFormat.getOrNull()' -> 'snippet.vertexFormatPerBuffer().firstOrNull()'
            vertexFormat = snippet.vertexFormat.getOrNull()
        }

        if (vertexMode == null) {
            //~ if >= 26.2 'vertexFormatMode' -> 'vertexFormatMode()'
            vertexMode = snippet.vertexFormatMode.getOrNull()
        }

        val builder = RenderPipeline.builder(snippet).withLocation("parallax/$location")
        vertexFormat?.let {
            //? if >= 26.2 {
            /*builder.withVertexBinding(0, it)
            builder.withPrimitiveTopology(vertexMode ?: PrimitiveTopology.QUADS)
            *///? } else {
            builder.withVertexFormat(it, vertexMode ?: VertexFormat.Mode.QUADS)
            //? }
        }

        vertexShader?.let {
            builder.withVertexShader(it)
        }

        fragmentShader?.let {
            builder.withFragmentShader(it)
        }

        depth.build(builder)
        if (!cull) {
            builder.withCull(false)
        }

        //? if >= 26.3 {
        /*val color0 = colorTargetState ?: if (snippet.activeColorTargetStateCount() == 0) ColorTargetState(BlendFunction.TRANSLUCENT) else null
        if (color0 != null) builder.withColorTargetState(color0)
        *///?}
        return builder.build()
    }
}
