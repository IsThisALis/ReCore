package org.gfs.recore.example.graphic;

    // ReCore imports
    // Core
import org.gfs.recore.core.ComponentLogic;

    // Graphics
import org.gfs.recore.graphics.window.*;
import org.gfs.recore.graphics.render.*;
import org.gfs.recore.graphics.render.scene.Scene;
import org.gfs.recore.graphics.camera.Camera;

public class Render implements ComponentLogic {

    // Instances
  VertexBufferObject vbo;
  VertexArrayObject vao;
  ElementBufferObject ebo;

  VertexBufferObject vbo1;
  VertexArrayObject vao1;
  ElementBufferObject ebo1;

  VertexBufferObject vbo2;
  VertexArrayObject vao2;
  ElementBufferObject ebo2;

  public ShaderManager shaderManager = new ShaderManager();
  public Scene scene = new Scene();

  private static Camera camera;
 
  Window window = Params.getWindowInst();
  Mesh mesh;
  Mesh mesh1;
  Mesh mesh2;
  Mesh mesh3;
  Mesh mesh5;

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

        float[] floor = {
    -20.0f, -20.0f, 0.0f, 0.0f, 1.0f,
     20.0f, -20.0f, 0.0f, 1.0f, 1.0f,
     20.0f,  20.0f, 0.0f, 1.0f, 0.0f,
    -20.0f,  20.0f, 0.0f, 0.0f, 0.0f
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

      vbo2 = new VertexBufferObject();
      vao2 = new VertexArrayObject();
      ebo2 = new ElementBufferObject();

      mesh = new Mesh(vao, vbo, ebo, shaderManager.textureManager.texture1, shaderManager.program);
      mesh1 = new Mesh(vao, vbo, ebo, shaderManager.textureManager.texture2, shaderManager.program);
      mesh2 = new Mesh(vao, vbo, ebo, shaderManager.textureManager.texture3, shaderManager.program);
      mesh3 = new Mesh(vao1, vbo1, ebo1, shaderManager.textureManager.texture, shaderManager.program);
      mesh5 = new Mesh(vao2, vbo2, ebo2, shaderManager.textureManager.texture4, shaderManager.program);

          // Initializing objects
      mesh.init(verticesTopLeft, indices, 6, true);
      mesh1.init(verticesTopLeft, indices, 6, true);
      mesh2.init(verticesTopLeft, indices, 6, true);
      mesh3.init(verticesBottomRight, indices, 6, true);
      mesh5.init(floor, indices, 6, true);

      mesh3.setPosition(4.0f, 4.0f);

      scene.setObjCount(2);
      scene.create();

      scene.addObj(0, mesh1);
      scene.addObj(1, mesh2);

      camera = new Camera(0.0f, 0.0f, 0.0f, shaderManager.program);
    }

    // Render cycle
  @Override
    public void update() {
      window.cleanWindow();

      shaderManager.program.use();
      camera.update();

      mesh5.draw();
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

  public static Camera getCamera() {
      return camera;
    }
}
