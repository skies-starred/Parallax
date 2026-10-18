#ifndef PARALLAX_SPHERE_GLSL
#define PARALLAX_SPHERE_GLSL

vec2 sphere(vec3 origin, vec3 direction) {
    float dot0 = dot(origin, direction);
    float dot1 = dot(origin, origin) - 1.0;
    float delta = dot0 * dot0 - dot1;

    if (delta < 0.0) {
        return vec2(1e9);
    }

    float root = sqrt(delta);
    float distance0 = -dot0 - root;
    float distance1 = -dot0 + root;

    if (distance1 <= 0.0) {
        return vec2(1e9);
    }

    return vec2((distance0 > 0.0) ? distance0 : distance1, distance1);
}

float alpha(vec3 point, vec3 direction, float width) {
    float h = length(cross(point, direction));
    float silhouette = (dot(point, direction) <= 0.0) ? (1.0 - smoothstep(0.0, max(width, 0.5) * max(fwidth(h), 0.001), abs(1.0 - h))) : 0.0;

    float ring0 = 1.0 - smoothstep(0.0, max(width, 0.5) * max(fwidth(point.y), 0.001), abs(point.y));
    float ring1 = 1.0 - smoothstep(0.0, max(width, 0.5) * max(fwidth(point.x), 0.001), abs(point.x));
    float ring2 = 1.0 - smoothstep(0.0, max(width, 0.5) * max(fwidth(point.z), 0.001), abs(point.z));

    return max(silhouette, max(ring0, max(ring1, ring2)));
}

#endif
