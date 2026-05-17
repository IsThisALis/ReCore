package rexample.gmobj;

import rexample.interaction.Collizion;
import rexample.resources.Resources;

import recore.graphics.render.Mesh;

import recore.graphics.window.Params;
import recore.graphics.window.Window;

import rephysics.collision.AABB;
import rephysics.shapes.Square;

public class Banana {

  Mesh mesh;
  Window window = Params.getWindowInst();
  AABB aabb = new AABB();

  boolean cooldown = false;

  public void create() {
    mesh = Resources.getScene().getObj(0);
    mesh.init(Square.getVertices(), Square.getIndices(), true);
    mesh.setScale(0.65f, 0.65f);
    mesh.setPosition(1.5f, 3.75f);
  }

  public void draw() {
    interact();
    if(!cooldown) {
      window.blend(true);
      mesh.draw();
      window.blend(false);
    }
  }

  public void interact() {
    if(Collizion.getPlayerState()) {
      cooldown = true;
    }
  }
}
