#ifndef PARALLAX_CYLINDER_GLSL
#define PARALLAX_CYLINDER_GLSL

vec2 cylinder(vec3 origin, vec3 direction) {
    float a = dot(direction.xz, direction.xz);
    float b = dot(origin.xz, direction.xz);
    float c = dot(origin.xz, origin.xz) - 1.0;

    float cylinder0 = -1e9;
    float cylinder1 = 1e9;

    if (a > 1e-5) {
        float delta = b * b - a * c;
        if (delta < 0.0) {
            return vec2(1e9);
        }

        float root = sqrt(delta);
        cylinder0 = (-b - root) / a;
        cylinder1 = (-b + root) / a;
    } else if (c > 0.0) {
        return vec2(1e9);
    }

    float slab0 = -1e9;
    float slab1 = 1e9;

    if (abs(direction.y) > 1e-5) {
        float height0 = (-1.0 - origin.y) / direction.y;
        float height1 = (1.0 - origin.y) / direction.y;
        slab0 = min(height0, height1);
        slab1 = max(height0, height1);
    } else if (abs(origin.y) > 1.0) {
        return vec2(1e9);
    }

    float near = max(cylinder0, slab0);
    float far = min(cylinder1, slab1);

    if (near > far || far <= 0.0) {
        return vec2(1e9);
    }

    return vec2((near > 0.0) ? near : far, far);
}

float alpha(vec3 position, float width) {
    float radius = length(position.xz);

    float width0 = width * max(fwidth(position.y), 0.001);
    float width1 = width * max(fwidth(radius), 0.001);
    float width2 = width * max(fwidth(position.x), 0.001);
    float width3 = width * max(fwidth(position.z), 0.001);

    float ring = (abs(position.y) < 0.999) ? (1.0 - smoothstep(0.0, width0, 1.0 - abs(position.y))) : (1.0 - smoothstep(0.0, width1, abs(1.0 - radius)));
    float strut = (abs(position.y) < 0.999) ? max(1.0 - smoothstep(0.0, width2, abs(position.x)), 1.0 - smoothstep(0.0, width3, abs(position.z))) : 0.0;

    return max(ring, strut);
}

#endif
