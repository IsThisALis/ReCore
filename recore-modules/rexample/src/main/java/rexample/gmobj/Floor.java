package rexample.gmobj;

import rexample.resources.Resources;

import recore.graphics.render.Mesh;
import recore.graphics.render.Renderer;

import rephysics.shapes.Square;

public class Floor {

  Mesh mesh;

  public void create() {
    mesh = Resources.getScene().getObj(2);
    mesh.init(Square.getVertices(), Square.getIndices(), true);
    mesh.setScale(50f, 50f);
  }

  public void draw() {
    Renderer.draw(mesh, Player.getCamera());
  }
}
