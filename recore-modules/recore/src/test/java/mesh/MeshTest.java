package mesh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterEach;

import recore.graphics.render.ElementBufferObject;
import recore.graphics.render.Mesh;
import recore.graphics.render.VertexArrayObject;
import recore.graphics.render.VertexBufferObject;
import recore.graphics.shaders.Shader;
import recore.graphics.shaders.ShaderProgram;
import recore.graphics.textures.Texture;

import recore.graphics.window.*;
import recore.util.IO;

public class MeshTest {

  static ShaderProgram program;
  static Shader shader;
  static Texture texture;

    @BeforeEach
  void init() {
    Window window = Params.getWindowInst();
    Params.getParams().setTitle("TexturesTest");
    Params.getParams().setWidth(1080);
    Params.getParams().setHeight(720);
    Params.getParams().setVsyncStatus(true);
    window.init();

    program = new ShaderProgram();
    texture = new Texture();
    texture.getParams().setUniform("ourTexture", 0);
  }

    @AfterEach
  void cleanup() {
    program = null;
    shader = null;
    texture = null;
  }

    @RepeatedTest(10)
  void load() {
        texture.getParams().setPath("tests/banana.png");

        IO io = new IO();
        io.loadTexture("tests/banana.png", texture);
        io = null;

        texture.createTexture(program);

        VertexArrayObject vao = new VertexArrayObject();
        VertexBufferObject vbo = new VertexBufferObject();
        ElementBufferObject ebo = new ElementBufferObject();

        Mesh mesh = new Mesh(vao, vbo, ebo, texture, program);

        float[] vertices = {
          // x     y     z    u   v
          -0.25f, 0.25f, 0f, 0f, 1f,
          0.25f, 0.25f, 0f, 1f, 1f,
          0.25f,-0.25f, 0f, 1f, 0f,
          -0.25f,-0.25f, 0f, 0f, 0f
        };
        int[] indices = {
          0, 1, 2,
          2, 3, 0
        };

        mesh.init(vertices, indices, true);
  }
}    
