package org.gfs.recore.core;

import org.gfs.recore.graphics.shaders.ShaderProgram;
import org.gfs.recore.graphics.textures.Texture;

public interface Renderable {

  void draw(ShaderProgram shaderProgram, Texture texture);
  
  void init(float[] vertices, int[] indices, boolean useTexture);

  void update(float[] vertices, int[] indices);

  void cleanup();

}
