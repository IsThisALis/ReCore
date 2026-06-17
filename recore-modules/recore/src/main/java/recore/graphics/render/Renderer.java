package recore.graphics.render;

import recore.graphics.camera.Camera;
import recore.graphics.camera.FOV;
import recore.graphics.window.Params;

public class Renderer {

  private static FOV fov = new FOV();

  public static boolean draw(Mesh mesh, Camera camera) {
    if(fov.inFov(mesh, camera)) {
      Params.getWindowInst().blend(true);
      mesh.draw();
      Params.getWindowInst().blend(false);
      return true;
    } else { return false; }
  }
}
