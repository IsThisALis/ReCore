package org.gfs.recore.core;

import org.gfs.recore.graphics.shaders.ShaderProgram;

public interface Renderable {

  void draw(ShaderProgram shaderProgram);
  
  void init(float[] vertices, int[] indices);

  void update(float[] vertices, int[] indices);

  void cleanup();

}
