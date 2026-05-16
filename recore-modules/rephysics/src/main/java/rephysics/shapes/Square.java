package rephysics.shapes;

public class Square {

  private static final float[] vertices = {
    // x     y     z    u   v
    -0.25f, 0.25f, 0f, 0f, 1f,
    0.25f, 0.25f, 0f, 1f, 1f,
    0.25f,-0.25f, 0f, 1f, 0f,
    -0.25f,-0.25f, 0f, 0f, 0f
  };

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
}
