package recore.graphics.render;

import recore.graphics.camera.Camera;
import recore.graphics.camera.FOV;

public class Renderer {

  public static void draw(Mesh mesh, Camera camera) {
    if(FOV.inFov(mesh, camera)) {
      mesh.draw();
    }
  }
}
