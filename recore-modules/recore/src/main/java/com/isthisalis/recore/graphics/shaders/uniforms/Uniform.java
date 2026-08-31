package com.isthisalis.recore.graphics.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

import lombok.NonNull;

/**
 * Shader uniform, soring location and uniform variable name.
 */
public record Uniform(
    int location,
    String name
    ) {

    /**
     * Gets new location from OpenGL with name.
     * @param name Variable name in shader.
     * @return New uniform with all needed data.
     */
    public static Uniform getUniform(@NonNull String name, int shaderProgramId) {
        return new Uniform(glGetUniformLocation(shaderProgramId, name), name);
    }
}
