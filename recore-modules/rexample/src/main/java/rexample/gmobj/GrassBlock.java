package rexample.gmobj;

import rexample.resources.Resources;

import recore.graphics.render.Mesh;

import rephysics.shapes.Square;

public class GrassBlock {

  Mesh body;

  public void create() {
    body = Resources.getScene().getObj(1);
    body.init(Square.getVertices(), Square.getIndices(), true);
  }

  public void draw() {
    body.setPosition(0f, -1.15f);
    body.draw();
    body.setPosition(0.5f, -1.15f);
    body.draw();
    body.setPosition(1f, -1.15f);
    body.draw();
    body.setPosition(1.5f, -1.15f);
    body.draw();
  }
}
