package com.isthisalis.recore.graphics.material;

import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.textures.Texture;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Material
 */
@RequiredArgsConstructor
public class Material {

  private @Getter @Setter float scaleX, scaleY;

  private @NonNull @Getter @Setter Texture texture;
  private @NonNull @Getter @Setter ShaderProgram program;


  public void setScale(float x, float y) {
    scaleX = x;
    scaleY = y;
  }

  public void bind() {
    program.use();
    texture.update();
    texture.bind();
  }
}
