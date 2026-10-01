package foo.starred.parallax.api.events

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory

object ParallaxEvents {
    val EXTRACT: Event<Extract> = EventFactory.createArrayBacked(Extract::class.java) { listeners ->
        Extract {
            for (listener in listeners) {
                listener.extract()
            }
        }
    }

    fun interface Extract {
        fun extract()
    }
}