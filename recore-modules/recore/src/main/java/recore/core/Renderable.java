package recore.core;

import recore.graphics.shaders.ShaderProgram;
import recore.graphics.textures.Texture;

public interface Renderable {

  void draw();
  
  void init(float[] vertices, int[] indices, int indicesNum, boolean useTexture);

  void update(float[] vertices, int[] indices, int indicesNum);

  void cleanup();

}
