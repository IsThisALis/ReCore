package rexample.gmobj;

import recore.graphics.render.Mesh;
import recore.util.Time;
import rexample.resources.Resources;

public class Projectile {

  float velocity = 10f;

  Mesh mesh;

  Time time = new Time();

  boolean flying;

  float[] vertices = {
    // x     y     z    u   v
    -0.25f, 0.25f, 0f, 0f, 1f,
    0.25f, 0.25f, 0f, 1f, 1f,
    0.25f,-0.25f, 0f, 1f, 0f,
    -0.25f,-0.25f, 0f, 0f, 0f
  };

  int[] indices = {
     0, 1, 2,
     2, 3, 0
  };

  public void init() {
    mesh = Resources.getScene().getObj(4);
    mesh.init(vertices, indices, true);
  }

  public void draw() {
    if(flying) {
      mesh.draw();
    }
  }

  public void setPosition(float x, float y) {
    mesh.setPosition(x, y);
  }

  public void run(boolean fly) {  
    flying = fly;
    if(flying) {
      velocity -= 9.8f * time.getDelta();
      float newY = mesh.getPosition().y + velocity * time.getDelta();
      float newX = mesh.getPosition().x + velocity * time.getDelta();

      if(newY <= 0f) {
        newY = 0f;
        flying = false;
        velocity = 10f;
      }
      mesh.setPosition(newX, newY);
    }
  }
}
