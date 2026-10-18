#version 330
//? if >= 26.3
//#extension GL_ARB_separate_shader_objects : require

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <parallax:sphere.glsl>

//$ layout '0' 'in' >> vec
in vec4 color0;
//$ layout '1' 'in' >> vec
in vec3 position0;
//$ layout '2' 'in' >> vec
in vec3 camera0;
//$ layout '3' 'in' >> float
in float radius0;
//$ layout '4' 'in' >> float
in float width0;

//$ layout '0' 'out' >> vec
out vec4 fragColor;

void main() {
    vec3 origin = camera0;
    vec3 direction = normalize(position0 - camera0);

    vec2 distances = sphere(origin, direction);
    if (distances.x >= 1e9) {
        discard;
    }

    vec3 bound = abs(origin);
    if (max(max(bound.x, bound.y), bound.z) > 1.0 && !gl_FrontFacing) {
        discard;
    }

    vec3 point0 = origin + distances.x * direction;
    float alpha0 = alpha(point0, direction, width0);

    vec3 point1 = origin + distances.y * direction;
    float alpha1 = alpha(point1, direction, width0);
    if (distances.y <= distances.x) {
        alpha1 = 0.0;
    }

    float alpha2 = alpha0 + alpha1 * (1.0 - alpha0);
    if (alpha2 <= 0.0) {
        discard;
    }

    fragColor = color0 * ColorModulator;
    fragColor.a *= alpha2;
}
