@file:Suppress("ConstPropertyName", "Unused")

package foo.starred.parallax

import foo.starred.parallax.core.queue.impl.ParallaxRenderQueue
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.Minecraft
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Parallax : ClientModInitializer {
    const val version: String = /*$ mod_version*/ "2026.10.2"
    const val id: String = /*$ mod_id*/ "parallax"
    const val name: String = /*$ mod_name*/ "Parallax"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger("Parallax")

    @JvmField
    val client: Minecraft = Minecraft.getInstance()

    override fun onInitializeClient() {
        LOGGER.info("Parallax initialising...")
        ParallaxRenderQueue.init()
        LOGGER.info("Parallax initialised.")
    }
}
