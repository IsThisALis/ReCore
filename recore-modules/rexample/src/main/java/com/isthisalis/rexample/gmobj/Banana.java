package com.isthisalis.rexample.gmobj;

import com.isthisalis.rexample.interaction.Collizion;
import com.isthisalis.rexample.resources.Resources;
import com.isthisalis.recore.graphics.material.Material;
import com.isthisalis.recore.graphics.render.Mesh;
import com.isthisalis.recore.graphics.render.Renderer;

import com.isthisalis.recore.graphics.window.Params;
import com.isthisalis.recore.graphics.window.Window;

import com.isthisalis.rephysics.collision.AABB;
import com.isthisalis.rephysics.shapes.Square;

public class Banana {

  Mesh mesh;
  Material material;
  Window window = Params.getWindowInst();
  AABB aabb = new AABB();

  boolean cooldown = false;

  public void create() {
    mesh = Resources.getScene().getObj(0);
    material = Resources.getMaterialMap().getObj("banana");
    material.setTransparency(true);
    mesh.init(Square.getVertices(), Square.getIndices(), true);
    mesh.setScale(0.65f, 0.65f);
    mesh.setPosition(1.5f, 1.75f);
  }

  public void draw() {
    interact();
    if(!cooldown) {
      material.bind();
      Renderer.draw(mesh, material, Player.getCamera());
    }
  }

  public void interact() {
    if(Collizion.getPlayerState()) {
      cooldown = true;
    }
  }
}
