package recore.core;

public interface Renderable {

  void draw();
  
  void init(float[] vertices, int[] indices, boolean useTexture);

  void update(float[] vertices, int[] indices);

  void cleanup();

}
