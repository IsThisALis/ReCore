package com.isthisalis.rexample.gmobj;

import com.isthisalis.rexample.resources.Resources;
import com.isthisalis.recore.graphics.material.Material;
import com.isthisalis.recore.graphics.render.Mesh;
import com.isthisalis.recore.graphics.render.Renderer;

import com.isthisalis.rephysics.shapes.Square;

public class GrassBlock {

  private Mesh mesh;
  private Material material;

  public void create() {
    mesh = Resources.getScene().getObj(1);
    material = Resources.getMaterialMap().getObj("grass_block");
    mesh.init(Square.getVertices(), Square.getIndices(), true);
  }

  public void draw() {
    mesh.setPosition(0f, -1.15f);
    Renderer.draw(mesh, material, Player.getCamera());
    mesh.setPosition(0.5f, -1.15f);
    Renderer.draw(mesh, material, Player.getCamera());
    mesh.setPosition(1f, -1.15f);
    Renderer.draw(mesh, material, Player.getCamera());
    mesh.setPosition(1.5f, -1.15f);
    Renderer.draw(mesh, material, Player.getCamera());
  }
}
