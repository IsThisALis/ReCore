package com.isthisalis.recore.graphics.render;

import com.isthisalis.recore.graphics.camera.Camera;
import com.isthisalis.recore.graphics.camera.FOV;
import com.isthisalis.recore.graphics.material.Material;

public class Renderer {

  private static FOV fov = new FOV();

  public static boolean draw(Mesh mesh, Material material, Camera camera) {
    if(fov.inFov(mesh, camera)) {
      material.bind();
      mesh.draw();
      return true;
    } else { return false; }
  }
}
