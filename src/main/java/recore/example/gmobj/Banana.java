package recore.example.gmobj;

import recore.example.resources.Resources;

import recore.graphics.render.Mesh;

import recore.graphics.window.Params;
import recore.graphics.window.Window;

public class Banana {

  Mesh mesh;
  Window window = Params.getWindowInst();

  float[] vertices = {
    // x     y     z    u     v
    0.5f, -0.5f, 0.0f, 0.0f, 1.0f,
    1.0f, -0.5f, 0.0f, 1.0f, 1.0f,
    1.0f, -1.0f, 0.0f, 1.0f, 0.0f,
    0.5f, -1.0f, 0.0f, 0.0f, 0.0f
  };

  int[] indices = {
     0, 1, 2,
     2, 3, 0
  };

  public void create() {
    mesh = Resources.getScene().getObj(0);
    mesh.init(vertices, indices, 6, true);
    mesh.setScale(0.65f, 0.65f, 0.65f);
    mesh.setPosition(4.5f, -3.75f);
  }

  public void draw() {
    window.blend(true);
    mesh.draw();
    window.blend(false);
  }
}
