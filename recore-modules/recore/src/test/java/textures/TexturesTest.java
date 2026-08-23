package textures;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.isthisalis.recore.graphics.shaders.Shader;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.textures.Texture;

import com.isthisalis.recore.graphics.window.*;
import com.isthisalis.recore.util.IO;

public class TexturesTest {

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
    .build(), null);

    program = new ShaderProgram();
  }

    @AfterAll
  static void cleanup() {
    program = null;
    shader = null;
    texture = null;
  }

    @RepeatedTest(10)
  void load() {

        IO io = new IO();
        texture = io.loadTexture("tests/banana.png");
        io = null;

        texture.createTexture(program, "ourTexture");
  }
}    
