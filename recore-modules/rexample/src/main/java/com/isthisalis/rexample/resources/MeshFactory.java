package com.isthisalis.rexample.resources;

import com.isthisalis.recore.graphics.render.*;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;

public class MeshFactory {

  public Mesh newMesh(ShaderProgram program) {
    VertexArrayObject vao = new VertexArrayObject();
    VertexBufferObject vbo = new VertexBufferObject();
    ElementBufferObject ebo = new ElementBufferObject();

    return new Mesh(vao, vbo, ebo, program);
  }
}
