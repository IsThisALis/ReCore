package org.gfs.recore.example.graphic;

import org.gfs.recore.core.ComponentLogic;

import org.gfs.recore.graphics.window.*;
import org.gfs.recore.graphics.render.*;

public class Render implements ComponentLogic {

  VertexBufferObject vbo;
  VertexArrayObject vao;
  ElementBufferObject ebo;

  VertexBufferObject vbo1;
  VertexArrayObject vao1;
  ElementBufferObject ebo1;



  public ShaderManager shaderManager = new ShaderManager();
 
  //Params params = Params.getParams();
  Window window = Params.getWindowInst();
  Mesh mesh;
  Mesh mesh1;

                float[] verticesTopLeft = {
    // x     y    z     u     v
    -1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
    -0.5f, 1.0f, 0.0f, 1.0f, 1.0f,
    -0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
    -1.0f, 0.5f, 0.0f, 0.0f, 0.0f
        };

        float[] verticesBottomRight = {
    // x     y     z    u     v
    0.5f, -0.5f, 0.0f, 0.0f, 1.0f,
    1.0f, -0.5f, 0.0f, 1.0f, 1.0f,
    1.0f, -1.0f, 0.0f, 1.0f, 0.0f,
    0.5f, -1.0f, 0.0f, 0.0f, 0.0f
        };

        int[] indices = {
            0, 1, 2,
            2, 3, 0
        };

  @Override
    public void init() {
      vbo = new VertexBufferObject();
      vao = new VertexArrayObject();
      ebo = new ElementBufferObject();

      vbo1 = new VertexBufferObject();
      vao1 = new VertexArrayObject();
      ebo1 = new ElementBufferObject();

      mesh = new Mesh(vao, vbo, ebo);
      mesh1 = new Mesh(vao1, vbo1, ebo1);
      mesh.init(verticesTopLeft, indices, 6, true);
      mesh1.init(verticesBottomRight, indices, 6, true);
      shaderManager.init();

    }
  @Override
    public void update() {
      window.cleanWindow();
      
      //shaderManager.textureManager.texture.params.setUniform("ourTexture", 0);
      mesh.draw(shaderManager.program1, shaderManager.textureManager.texture1);

      //shaderManager.textureManager.texture1.params.setUniform("ourTexture", 1);
      mesh1.draw(shaderManager.program2, shaderManager.textureManager.texture);

    }
  @Override
    public void cleanup() {
      
    }
}
