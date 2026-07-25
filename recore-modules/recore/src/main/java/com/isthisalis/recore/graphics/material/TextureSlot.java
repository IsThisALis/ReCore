package com.isthisalis.recore.graphics.material;

import com.isthisalis.recore.graphics.shaders.uniforms.TextureUniform;
import com.isthisalis.recore.graphics.textures.Texture;

/**
 * TextureSlot
 */
public record TextureSlot(TextureUniform uniform, Texture texture) {}
