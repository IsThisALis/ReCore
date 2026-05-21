package recore.graphics.camera;

import recore.graphics.render.Mesh;

public class FOV {

  private float cLeft, cRight, cBottom, cTop;
  private float mLeft, mRight, mBottom, mTop;

  private float meshX, meshY;
  private float camX, camY;

  private float w, h;

  private float halfX, halfY;

  public boolean inFov(Mesh mesh, Camera camera) {
    w = camera.getFOV().x;
    h = camera.getFOV().y;

    meshX = mesh.getPosition().x;
    meshY = mesh.getPosition().y;
    halfX = getHalfWidth(mesh) * mesh.getScale().x;
    halfY = getHalfHeight(mesh) * mesh.getScale().y;

    camX = camera.getPosition().x;
    camY = camera.getPosition().y;

    cLeft = camX - w;
    cRight = camX + w;
    cBottom = camY - h;
    cTop = camY + h;

    mLeft = meshX - halfX;
    mRight = meshX + halfX; 
    mBottom = meshY - halfY;
    mTop = meshY + halfY;

    return !(mRight < cLeft || mLeft > cRight || mTop < cBottom || mBottom > cTop); 
  }


  private final float getHalfWidth(Mesh mesh) {
    float max = 0;
    for (int i = 0; i < mesh.getVertices().length; i += 5) {
        float x = Math.abs(mesh.getVertices()[i]);
        if (x > max) max = x;
    }
    return max;
  }
  
  private final float getHalfHeight(Mesh mesh) {
   float max = 0;
    for (int i = 1; i < mesh.getVertices().length; i += 5) {
        float x = Math.abs(mesh.getVertices()[i]);
        if (x > max) max = x;
    }
    return max; 
  }
}
