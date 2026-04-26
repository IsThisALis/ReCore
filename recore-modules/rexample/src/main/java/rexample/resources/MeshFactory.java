package rexample.resources;

import recore.graphics.render.*;
import recore.graphics.shaders.ShaderProgram;
import recore.graphics.textures.Texture;

public class MeshFactory {

  public Mesh newMesh(ShaderProgram shaderProgram, Texture texture) {
    VertexArrayObject vao = new VertexArrayObject();
    VertexBufferObject vbo = new VertexBufferObject();
    ElementBufferObject ebo = new ElementBufferObject();

    return new Mesh(vao, vbo, ebo, texture, shaderProgram);
  }

  public Mesh newMesh(ShaderProgram program) {
    VertexArrayObject vao = new VertexArrayObject();
    VertexBufferObject vbo = new VertexBufferObject();
    ElementBufferObject ebo = new ElementBufferObject();

    return new Mesh(vao, vbo, ebo, program);
  }
}
