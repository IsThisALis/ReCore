package rephysics.shapes;

import org.joml.Vector2f;

import rephysics.collision.AABB;

public class Circle {

  public float radius;
  private AABB aabb;


  public void setRadius(float value) {
    radius = value;
  }

  public void setRadius(int value) {
    radius = value;
  }


  public final AABB getAABB() {
    if(aabb == null) {
      aabb = new AABB(new Vector2f(-radius, -radius), new Vector2f(radius, radius));
    }

    return aabb;
  }
}
