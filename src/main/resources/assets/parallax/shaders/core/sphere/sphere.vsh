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
out float radius0;
//$ layout '4' 'out' >> float
out float width0;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    color0 = Color;
    position0 = Normal;
    radius0 = UV0.x;
    width0 = abs(UV0.y);

    vec3 vector0 = Position / UV0.x;
    camera0 = Normal - vector0;
}
