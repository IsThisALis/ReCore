package rexample.gmobj;

import rexample.resources.Resources;
import recore.graphics.render.Mesh;
import recore.graphics.render.Renderer;

import rephysics.shapes.Square;

public class GrassBlock {

  private Mesh mesh;

  public void create() {
    mesh = Resources.getScene().getObj(1);
    mesh.init(Square.getVertices(), Square.getIndices(), true);
  }

  public void draw() {
    mesh.setPosition(0f, -1.15f);
    Renderer.draw(mesh, Player.getCamera());
    mesh.setPosition(0.5f, -1.15f);
    Renderer.draw(mesh, Player.getCamera());
    mesh.setPosition(1f, -1.15f);
    Renderer.draw(mesh, Player.getCamera());
    mesh.setPosition(1.5f, -1.15f);
    Renderer.draw(mesh, Player.getCamera());
  }
}
