package org.gfs.recore.example.graphic;

    // ReCore imports
    // Core
import org.gfs.recore.core.ComponentLogic;

    // Graphics
import org.gfs.recore.graphics.window.*;
import org.gfs.recore.graphics.render.*;

public class Render implements ComponentLogic {

    // Instances
  VertexBufferObject vbo;
  VertexArrayObject vao;
  ElementBufferObject ebo;

  VertexBufferObject vbo1;
  VertexArrayObject vao1;
  ElementBufferObject ebo1;

  public ShaderManager shaderManager = new ShaderManager();
 
  Window window = Params.getWindowInst();
  Mesh mesh;
  Mesh mesh1;
  Mesh mesh2;
  Mesh mesh3;

    // Objects data
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
          // Initializing instances
      vbo = new VertexBufferObject();
      vao = new VertexArrayObject();
      ebo = new ElementBufferObject();

      vbo1 = new VertexBufferObject();
      vao1 = new VertexArrayObject();
      ebo1 = new ElementBufferObject();

      mesh = new Mesh(vao, vbo, ebo);
      mesh1 = new Mesh(vao, vbo, ebo);
      mesh2 = new Mesh(vao, vbo, ebo);
      mesh3 = new Mesh(vao1, vbo1, ebo1);

          // Initializing objects
      mesh.init(verticesTopLeft, indices, 6, true);
      mesh1.init(verticesTopLeft, indices, 6, true);
      mesh2.init(verticesTopLeft, indices, 6, true);
      mesh3.init(verticesBottomRight, indices, 6, true);

          // Initializing shaders
      shaderManager.init();

    }

    // Render cycle
  @Override
    public void update() {
      window.cleanWindow();

      mesh.draw(shaderManager.program1, shaderManager.textureManager.texture1);

      window.blend(true); 
      mesh1.draw(shaderManager.program2, shaderManager.textureManager.texture2);
      mesh2.draw(shaderManager.program3, shaderManager.textureManager.texture3);
      
      window.blend(false);
      mesh3.draw(shaderManager.program, shaderManager.textureManager.texture);

    }
  @Override
    public void cleanup() {
      // TODO: Cleanup
    }
}
