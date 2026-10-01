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

    float thickness = clamp(max(width0, 0.5) * aa, 0.0, 1.0);

    float outer = clamp((1.0 - dist) / aa, 0.0, 1.0);
    float inner = clamp((dist - (1.0 - thickness)) / aa, 0.0, 1.0);
    float alpha = outer * inner;
    if (alpha <= 0.0) discard;

    fragColor = color0 * ColorModulator;
    fragColor.a *= alpha;
}
