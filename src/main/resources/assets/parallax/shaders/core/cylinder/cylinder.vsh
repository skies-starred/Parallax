#version 330
//? if >= 26.3
//#extension GL_ARB_separate_shader_objects : require

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

//$ layout '0' 'in' >> vec
in vec3 Position;
//$ layout '1' 'in' >> vec
in vec2 UV0;
//$ layout '2' 'in' >> vec
in vec4 Color;
//$ layout '3' 'in' >> vec
in vec3 Normal;

//$ layout '0' 'out' >> vec
out vec4 color0;
//$ layout '1' 'out' >> vec
out vec3 position0;
//$ layout '2' 'out' >> vec
out vec3 camera0;
//$ layout '3' 'out' >> float
out float width0;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    color0 = Color;
    position0 = Normal;

    float raw0 = abs(UV0.y);
    float width1 = floor(raw0 / 1000.0);
    float height0 = raw0 - width1 * 1000.0;
    width0 = max(width1, 1.0);

    vec3 scale0 = vec3(UV0.x, height0, UV0.x);
    vec3 vector0 = Position / scale0;
    camera0 = Normal - vector0;
}
