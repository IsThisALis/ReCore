package recore.example.gmobj;

import recore.example.resources.Resources;
import recore.graphics.render.Mesh;

public class Floor {

  Mesh mesh;

  float[] vertices = {
    -20.0f, -20.0f, 0.0f, 0.0f, 1.0f,
     20.0f, -20.0f, 0.0f, 1.0f, 1.0f,
     20.0f,  20.0f, 0.0f, 1.0f, 0.0f,
    -20.0f,  20.0f, 0.0f, 0.0f, 0.0f
  };

  int[] indices = {
     0, 1, 2,
     2, 3, 0
  };

  public void create() {
    mesh = Resources.getScene().getObj(2);
    mesh.init(vertices, indices, 6, true);
  }

  public void draw() {
    mesh.draw();
  }

}
