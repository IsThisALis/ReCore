package com.isthisalis.rephysics.shapes;

import com.isthisalis.rephysics.collision.AABB;

public class Square {

    // Object collision, it is recommended to use this pre-set to avoid bugs
  private static final AABB aabb = new AABB();


    // Initializing collision data
  static {
    aabb.setMin(-0.5f, -0.5f);
    aabb.setMax(0.5f, 0.5f);
  }


    // Object vertices
  private static final float[] vertices = {
    // x     y     z    u   v
    -0.5f, 0.5f, 0f, 0f, 1f,
    0.5f, 0.5f, 0f, 1f, 1f,
    0.5f,-0.5f, 0f, 1f, 0f,
    -0.5f,-0.5f, 0f, 0f, 0f
  };

    // Object indices
  private static final int[] indices = {
     0, 1, 2,
     2, 3, 0
  };


    /**
     * Getter for vertices data (float).
     * @return Vertices data array.
     */
  public static final float[] getVertices() {
    return vertices;
  }


    /**
     * Getter for indices data.
     * @return Indices data array (int).
     */
  public static final int[] getIndices() {
    return indices;
  }


    /**
     * Getter for collision data
     * @return Object collision instance
     */
  public static final AABB getAABB() {
    return aabb;
  }
}
