package com.isthisalis.recore.core;

import com.isthisalis.recore.graphics.camera.Camera;
import com.isthisalis.recore.graphics.material.Material;
import com.isthisalis.recore.graphics.render.Mesh;
import com.isthisalis.recore.graphics.render.Renderer;

public abstract class Renderable {

  private Mesh mesh;
  private Material material;
  private Camera camera;


  public Renderable(Mesh mesh, Material material, Camera camera) {
    this.mesh = mesh;
    this.material = material;
    this.camera = camera;
  }


  public void render() {
    Renderer.draw(mesh, material, camera);
  } 


  public void setMaterial(Material material) {
    this.material = material;
  }


  public void setMesh(Mesh mesh) {
    this.mesh = mesh;
  }


  public void setCamera(Camera camera) {
    this.camera = camera;
  }


  public abstract void cleanup();
}
