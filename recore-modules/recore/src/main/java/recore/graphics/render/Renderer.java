package recore.graphics.render;

import recore.graphics.camera.Camera;
import recore.graphics.camera.FOV;

public class Renderer {

  private static FOV fov = new FOV();

  public static boolean draw(Mesh mesh, Camera camera) {
    if(fov.inFov(mesh, camera)) {
      mesh.draw();
      return true;
    } else { return false; }
  }
}
