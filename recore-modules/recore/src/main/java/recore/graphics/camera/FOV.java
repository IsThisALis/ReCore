package recore.graphics.camera;

import org.joml.Vector2f;

import recore.graphics.render.Mesh;

public class FOV {

  private static Vector2f fov = new Vector2f();
  private static Vector2f meshPosition = new Vector2f();

  public static boolean inFov(Mesh mesh, Camera camera) {
    meshPosition.x = mesh.getPosition().x;
    meshPosition.y = mesh.getPosition().y;

    fov.x = camera.getFOV().x;
    fov.y = camera.getFOV().y;

    return fov.x >= meshPosition.x && fov.y >= meshPosition.y;
  }
}
