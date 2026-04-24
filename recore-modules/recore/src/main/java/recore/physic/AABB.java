package recore.physic;

import recore.graphics.render.Mesh;

public class AABB {

  float[] vertices;

  public float[] minMax(Mesh object) {

    float minX = Float.MAX_VALUE;
    float maxX = -Float.MAX_VALUE;
    float minY = Float.MAX_VALUE;
    float maxY = -Float.MAX_VALUE;
    vertices = object.getVertices();

    for (int i = 0; i < vertices.length; i += 5) {
      float x = vertices[i];
      float y = vertices[i + 1];
    
      if (x < minX) minX = x;
      if (x > maxX) maxX = x;
      if (y < minY) minY = y;
      if (y > maxY) maxY = y;
    }
  return new float[] {minX, maxX, minY, maxY};
  }

  public boolean check(Mesh object, Mesh object1) {
   return true; 
  }
}
