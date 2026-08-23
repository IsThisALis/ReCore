package com.isthisalis.recore.graphics.camera;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.shaders.uniforms.Uniform;
import com.isthisalis.recore.util.Vec2f;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Builder.Default;

@Data
@AllArgsConstructor
@Builder
/**
 * Camera configuration data class.
 */
public class Configuration {
    
    /**
     * Camera in-world position.
     */
    private @Default Vec2f pos = new Vec2f(0f, 0f);

    /**
     * Shader program with attached shaders.
     */
    private ShaderProgram shaderProgram;

    /**
     * Window configuration. Used in projection matrix.
     * @see {@link com.isthisalis.recore.graphics.camera.Camera#getProjectionMatrix()}
     */
    private com.isthisalis.recore.graphics.window.Configuration window;
    private Uniform uniform;

    /**
     * Sets view projection matrix uniform name.
     * @param name View projection matrix name in shader.
     */
    public void setUniform(@NonNull String name) {
        uniform = new Uniform(glGetUniformLocation(shaderProgram.getId(), name), name);
    }
}
