#version 330 core

layout(location = 0) in vec3 aPos;
layout(location = 2) in vec2 aTexCoord;

out vec2 TexCoord;

uniform mat4 uModelMatrix;
uniform mat4 uVPMatrix;

uniform vec2 uScale;
uniform vec2 uPosition

void main() {
    gl_Position = uVPMatrix * uModelMatrix * vec4(aPos, 1.0);
    TexCoord = aTexCoord;
}

