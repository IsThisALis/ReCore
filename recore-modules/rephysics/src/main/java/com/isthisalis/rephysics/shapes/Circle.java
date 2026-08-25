package com.isthisalis.rephysics.shapes;

import org.joml.Vector2f;

import com.isthisalis.rephysics.collision.AABB;

import lombok.Getter;
import lombok.Setter;

public class Circle {

  public @Getter @Setter float radius = 0f;
  private AABB aabb;


  public final AABB getAABB() {
    if(aabb == null) {
      aabb = new AABB(new Vector2f(-radius, -radius), new Vector2f(radius, radius));
    }

    return aabb;
  }
}
