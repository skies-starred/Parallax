#version 330
//? if >= 26.3
//#extension GL_ARB_separate_shader_objects : require

#moj_import <minecraft:dynamictransforms.glsl>

//$ layout '0' 'in' >> vec
in vec4 color0;
//$ layout '1' 'in' >> vec
in vec2 coord0;
//$ layout '2' 'in' >> float
in float width0;

//$ layout '0' 'out' >> vec
out vec4 fragColor;

void main() {
    float dist = length(coord0);
    float delta = length(vec2(dFdx(dist), dFdy(dist)));
    float aa = max(delta, 0.0001);

    float alpha = clamp((1.0 - dist) / aa, 0.0, 1.0);
    if (alpha <= 0.0) discard;

    fragColor = color0 * ColorModulator;
    fragColor.a *= alpha;
}
