package org.gfs.recore.core;

import org.gfs.recore.graphics.shaders.ShaderProgram;
import org.gfs.recore.graphics.textures.Texture;

public interface Renderable {

  void draw();
  
  void init(float[] vertices, int[] indices, int indicesNum, boolean useTexture);

  void update(float[] vertices, int[] indices, int indicesNum);

  void cleanup();

}
