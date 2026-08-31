package com.isthisalis.recore.graphics.shaders.uniforms;

/**
 * Texture uniform, storing name, texture unit and uniform location given by OpenGL.
 */
public record TextureUniform(
    int location, 
    int unit,
    String name
) {}
