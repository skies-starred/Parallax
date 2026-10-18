package foo.starred.parallax.core.backend.pipeline.depth

import com.mojang.blaze3d.pipeline.RenderPipeline
import java.util.*

//~ if >= 26.3 'blaze3d' -> 'renderpearl.api'
import com.mojang.blaze3d.pipeline.DepthStencilState
//~ if >= 26.3 'blaze3d.platform' -> 'renderpearl.api.pipeline'
import com.mojang.blaze3d.platform.CompareOp

enum class ParallaxPipelineDepth(val vanilla: Optional<DepthStencilState>) {
    NONE(Optional.empty()),
    EQUAL(Optional.of(DepthStencilState(CompareOp.EQUAL, false))),
    //~ if >= 26.2 'LESS_THAN_OR_EQUAL' -> 'GREATER_THAN_OR_EQUAL'
    LEQUAL(Optional.of(DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))),
    //~ if >= 26.2 'CompareOp.LESS_THAN,' -> 'CompareOp.GREATER_THAN,'
    LESS(Optional.of(DepthStencilState(CompareOp.LESS_THAN, false))),
    //~ if >= 26.2 'CompareOp.GREATER_THAN,' -> 'CompareOp.LESS_THAN,'
    GREATER(Optional.of(DepthStencilState(CompareOp.GREATER_THAN, false)));

    fun build(builder: RenderPipeline.Builder) {
        builder.withDepthStencilState(vanilla)
    }
}
