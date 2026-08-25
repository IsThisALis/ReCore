package com.isthisalis.rephysics.shapes;

import lombok.Getter;

public class Triangle { }

class RightTriangle {
  
  private static final @Getter float[] vertices = {
    // x     y     z    u   v
    -0.25f, 0.25f, 0f, 0f, 1f,
     0.25f, 0.25f, 0f, 1f, 1f,
     0.25f,-0.25f, 0f, 1f, 0f
  };

  private static final @Getter int[] indices = {
     0, 1, 2
  };
}


class EquilateralTriangle {

  private static final @Getter float[] vertices = {
    // x      y     z    u    v
     0.0f,  0.25f, 0f,  0f,  1f,  
    -0.25f,-0.25f, 0f,  1f,  1f,  
     0.25f,-0.25f, 0f,  1f,  0f   
  };

  private static final @Getter int[] indices = {
     0, 1, 2
  };
}
