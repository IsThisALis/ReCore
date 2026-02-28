package org.gfs.recore.example.shaders;

//Rendering systems imports
import org.gfs.recore.graphics.render.ElementBufferObject;
import org.gfs.recore.graphics.render.Mesh;
import org.gfs.recore.graphics.render.VertexArrayObject;
import org.gfs.recore.graphics.render.VertexBufferObject;

//Wimdow import
import org.gfs.recore.graphics.window.Window;

public class Renderer {

  Window window = new Window();
   
  VertexBufferObject vbo;
  VertexArrayObject vao;
  ElementBufferObject ebo;

  VertexBufferObject vbo1;
  VertexArrayObject vao1;
  ElementBufferObject ebo1;

  public ShaderManager shaderManager = new ShaderManager();

  Mesh mesh1;
  Mesh mesh2;

float[] verticesTopLeft = {
    // x      y     z    r     g     b     u      v
    -1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f,  
    -0.5f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f,
    -0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
    -1.0f, 0.5f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f
};

float[] verticesBottomRight = {
    // x     y     z     r     g     b     u     v
    0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
    1.0f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
    1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
    0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f
};        
        int[] indices = {
            0, 1, 2,
            2, 3, 0
        };
        /*int[] indices1 = {
          0, 1, 2,
          2, 3, 0
        };*/


  public void init() {
    shaderManager.init();
    vbo = new VertexBufferObject();
    vao = new VertexArrayObject();
    ebo = new ElementBufferObject();

    vbo1 = new VertexBufferObject();
    vao1 = new VertexArrayObject();
    ebo1 = new ElementBufferObject();


    mesh1 = new Mesh(vao, vbo, ebo);
    mesh1.init(verticesTopLeft, indices);

    mesh2 = new Mesh(vao1, vbo1, ebo1);
    mesh2.init(verticesBottomRight, indices);

  }

  public void render() {

    window.cleanWindow();

    mesh1.draw(shaderManager.program1);
    mesh2.draw(shaderManager.program1);

  }
}
