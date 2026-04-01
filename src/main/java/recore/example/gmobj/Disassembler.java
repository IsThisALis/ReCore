package recore.example.gmobj;

import recore.example.resources.Resources;
import recore.graphics.render.Mesh;
import recore.graphics.window.*;

public class Disassembler {

  Window window = Params.getWindowInst();
  Mesh bottom;
  Mesh spinner;
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
    bottom = Resources.getScene().getObj(0);
    spinner = Resources.getScene().getObj(1);
    body = Resources.getScene().getObj(2);

    bottom.init(vertices, indices, 6, true);
    spinner.init(vertices, indices, 6, true);
    body.init(vertices, indices, 6, true);
  }

  public void draw() {
    bottom.draw();
    window.blend(true);
    spinner.draw();
    body.draw();
    window.blend(false);
  }

}
