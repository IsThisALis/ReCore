package com.isthisalis.recore.graphics.material;

import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.textures.Texture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Material
 */
@RequiredArgsConstructor
public class Material {

  private @Getter @Setter float scaleX, scaleY;
  private @Getter @Setter boolean transparent = false;

  private final Texture texture;
  private final ShaderProgram program;


  public void setScale(float x, float y) {
    scaleX = x;
    scaleY = y;
  }

  public void bind() {
    program.use();
    texture.bind();
  }
}
