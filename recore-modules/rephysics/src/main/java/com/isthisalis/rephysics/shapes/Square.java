package com.isthisalis.rephysics.shapes;

import com.isthisalis.rephysics.collision.AABB;

import lombok.Getter;

public class Square {

    // Object collision, it is recommended to use this pre-set to avoid bugs
  private static final @Getter AABB aabb = new AABB();


    // Initializing collision data
  static {
    aabb.setMin(-0.5f, -0.5f);
    aabb.setMax(0.5f, 0.5f);
  }


    // Object vertices
  private static final @Getter float[] vertices = {
    // x     y     z    u   v
    -0.5f, 0.5f, 0f, 0f, 1f,
    0.5f, 0.5f, 0f, 1f, 1f,
    0.5f,-0.5f, 0f, 1f, 0f,
    -0.5f,-0.5f, 0f, 0f, 0f
  };

    // Object indices
  private static final @Getter int[] indices = {
     0, 1, 2,
     2, 3, 0
  };
}
