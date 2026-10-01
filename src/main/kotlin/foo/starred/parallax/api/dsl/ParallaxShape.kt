package foo.starred.parallax.api.dsl

object ParallaxShape {
    inline fun draw(action: ParallaxShapeScope.() -> Unit) {
        ParallaxShapeScope(true).action()
    }
}
