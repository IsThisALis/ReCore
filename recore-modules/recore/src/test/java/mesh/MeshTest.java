package mesh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.isthisalis.recore.graphics.render.*;
import com.isthisalis.recore.graphics.shaders.*;
import com.isthisalis.recore.graphics.textures.Texture;

import com.isthisalis.recore.graphics.window.*;
import com.isthisalis.recore.util.IO;

import app.Application;

public class MeshTest {

  static ShaderProgram program;
  static Shader shader;
  static Texture texture;

    @BeforeAll
  static void init() {
    Window window = new Window();
    window.init(Configuration.builder()
    .width(320)
    .height(240)
    .vsync(true)
    .title("MeshTest")
    .build(), new Application());
  }

    @BeforeEach
  void assets() {
    program = new ShaderProgram();
  }

    @AfterEach
  void cleanup() {
    program = null;
    shader = null;
    texture = null;
  }

    @RepeatedTest(10)
  void load() {

        IO io = new IO();
        texture = io.loadTexture("tests/banana.png");

        texture.createTexture(program, "ourTexture");

        Mesh mesh = new Mesh();

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
