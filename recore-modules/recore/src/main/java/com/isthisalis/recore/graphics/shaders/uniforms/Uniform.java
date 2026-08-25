package com.isthisalis.recore.graphics.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

import lombok.NonNull;

/**
 * Uniform
 */
public record Uniform(
    int location,
    String name
    ) {
    /**
     * Sets view projection matrix uniform name.
     * @param name View projection matrix name in shader.
     */
    public static Uniform getUniform(@NonNull String name, int shaderProgramId) {
        return new Uniform(glGetUniformLocation(shaderProgramId, name), name);
    }
}
