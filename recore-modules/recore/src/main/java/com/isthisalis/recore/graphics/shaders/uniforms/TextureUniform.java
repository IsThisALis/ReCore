package com.isthisalis.recore.graphics.shaders.uniforms;

/**
 * TextureUniform
 */
public record TextureUniform(
    int location, 
    int unit,
    String name
) {}
