package foo.starred.parallax.api.primitives

import foo.starred.parallax.core.data.impl.ExtractedParallaxText
import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.minecraft.network.chat.Component
import net.minecraft.util.FormattedCharSequence
import net.minecraft.world.phys.Vec3

object ParallaxText {
    fun formatted(text: FormattedCharSequence, pos: Vec3, color: Int = -1, background: Int = 0, scale: Float = 1f, depth: Boolean = false, shadow: Boolean = true, increase: Boolean = false, factor: Float = 3f) {
        ParallaxRenderQueue.texts.add(ExtractedParallaxText(text, pos, color, background, scale, shadow, depth, increase, factor))
    }

    fun component(text: Component, pos: Vec3, color: Int = -1, background: Int = 0, scale: Float = 1f, depth: Boolean = false, shadow: Boolean = true, increase: Boolean = false, factor: Float = 3f) {
        formatted(text.visualOrderText, pos, color, background, scale, depth, shadow, increase, factor)
    }

    fun string(text: String, pos: Vec3, color: Int = -1, background: Int = 0, scale: Float = 1f, depth: Boolean = false, shadow: Boolean = true, increase: Boolean = false, factor: Float = 3f) {
        component(Component.literal(text), pos, color, background, scale, depth, shadow, increase, factor)
    }
}
