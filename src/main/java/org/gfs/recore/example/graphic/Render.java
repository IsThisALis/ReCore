package org.gfs.recore.example.graphic;

    // ReCore imports
    // Core
import org.gfs.recore.core.ComponentLogic;

    // Graphics
import org.gfs.recore.graphics.window.*;
import org.gfs.recore.graphics.render.*;
import org.gfs.recore.graphics.render.scene.Scene;

public class Render implements ComponentLogic {

    // Instances
  VertexBufferObject vbo;
  VertexArrayObject vao;
  ElementBufferObject ebo;

  VertexBufferObject vbo1;
  VertexArrayObject vao1;
  ElementBufferObject ebo1;

  public ShaderManager shaderManager = new ShaderManager();
  public Scene scene = new Scene();
 
  Window window = Params.getWindowInst();
  Mesh mesh;
  Mesh mesh1;
  Mesh mesh2;
  public Mesh mesh3;

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

          // Initializing shaders
      shaderManager.init();

          // Initializing instances
      vbo = new VertexBufferObject();
      vao = new VertexArrayObject();
      ebo = new ElementBufferObject();

      vbo1 = new VertexBufferObject();
      vao1 = new VertexArrayObject();
      ebo1 = new ElementBufferObject();

      mesh = new Mesh(vao, vbo, ebo, shaderManager.textureManager.texture1, shaderManager.program);
      mesh1 = new Mesh(vao, vbo, ebo, shaderManager.textureManager.texture2, shaderManager.program);
      mesh2 = new Mesh(vao, vbo, ebo, shaderManager.textureManager.texture3, shaderManager.program);
      mesh3 = new Mesh(vao1, vbo1, ebo1, shaderManager.textureManager.texture, shaderManager.program);

          // Initializing objects
      mesh.init(verticesTopLeft, indices, 6, true);
      mesh1.init(verticesTopLeft, indices, 6, true);
      mesh2.init(verticesTopLeft, indices, 6, true);
      mesh3.init(verticesBottomRight, indices, 6, true);

      scene.setObjCount(2);
      scene.create();

      scene.addObj(0, mesh1);
      scene.addObj(1, mesh2);
    }

    // Render cycle
  @Override
    public void update() {
      window.cleanWindow();

      mesh.draw();

      window.blend(true); 
      scene.draw();
      
      window.blend(false);
      mesh3.draw();

    }
  @Override
    public void cleanup() {
      // TODO: Cleanup
    }

    public void updateData(float[] vertices, int[] indices, Mesh meshCurr) {
      meshCurr.update(vertices, indices, 6);
    }
}
