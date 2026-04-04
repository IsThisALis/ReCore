package recore.example.gmobj;

import recore.example.resources.Resources;
import recore.graphics.render.Mesh;
import recore.graphics.window.*;

public class GrassBlock {

  Window window = Params.getWindowInst();
  Mesh body;

  float[] vertices = {
    // x     y    z     u     v
    -1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
    -0.5f, 1.0f, 0.0f, 1.0f, 1.0f,
    -0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
    -1.0f, 0.5f, 0.0f, 0.0f, 0.0f
  };

  int[] indices = {
     0, 1, 2,
     2, 3, 0
  };

  public void create() {
    body = Resources.getScene().getObj(1);
    body.init(vertices, indices, 6, true);
  }

  public void draw() {
    body.draw();
  }

}
