package com.isthisalis.rephysics.shapes;

public class Triangle { }

class RightTriangle {
  
  private static final float[] vertices = {
    // x     y     z    u   v
    -0.25f, 0.25f, 0f, 0f, 1f,
     0.25f, 0.25f, 0f, 1f, 1f,
     0.25f,-0.25f, 0f, 1f, 0f
  };

  private static final int[] indices = {
     0, 1, 2
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
}



class EquilateralTriangle {

  private static final float[] vertices = {
    // x      y     z    u    v
     0.0f,  0.25f, 0f, 0.5f, 1f,  // 0: верх
    -0.25f,-0.25f, 0f, 0f,   0f,  // 1: низ-лево
     0.25f,-0.25f, 0f, 1f,   0f   // 2: низ-право
  };

  private static final int[] indices = {
     0, 1, 2
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
}
