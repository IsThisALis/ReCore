package rexample.gmobj;

import rexample.interaction.Collizion;
import rexample.resources.Resources;

import recore.graphics.render.Mesh;

import recore.graphics.window.Params;
import recore.graphics.window.Window;

import rephysics.collision.AABB;

public class Banana {

  Mesh mesh;
  Window window = Params.getWindowInst();
  AABB aabb = new AABB();

  boolean cooldown = false;

  float[] vertices = {
    // x     y     z    u     v
    0.5f, -0.5f, 0.0f, 0.0f, 1.0f,
    1.0f, -0.5f, 0.0f, 1.0f, 1.0f,
    1.0f, -1.0f, 0.0f, 1.0f, 0.0f,
    0.5f, -1.0f, 0.0f, 0.0f, 0.0f
  };

  int[] indices = {
     0, 1, 2,
     2, 3, 0
  };

  public void create() {
    mesh = Resources.getScene().getObj(0);
    mesh.init(vertices, indices, 6, true);
    mesh.setScale(0.65f, 0.65f, 0.65f);
    mesh.setPosition(4.5f, -3.75f);
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

  public void setPosition(float x, float y) {
    mesh.setPosition(x, y);
  }
}
