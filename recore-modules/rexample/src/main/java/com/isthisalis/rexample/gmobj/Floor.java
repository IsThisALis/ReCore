package com.isthisalis.rexample.gmobj;

import com.isthisalis.rexample.resources.Resources;
import com.isthisalis.recore.graphics.material.Material;

import com.isthisalis.recore.graphics.render.Mesh;
import com.isthisalis.recore.graphics.render.Renderer;

import com.isthisalis.rephysics.shapes.Square;

public class Floor {

  private Mesh mesh;
  private Material material;

  public void create() {
    mesh = Resources.getScene().getObj(2);
    material = Resources.getMaterialMap().getObj("grass_floor");
    mesh.init(Square.getVertices(), Square.getIndices(), true);
    mesh.setScale(50f, 50f);
  }

  public void draw() {
    Renderer.draw(mesh, material, Player.getCamera());
  }
}
