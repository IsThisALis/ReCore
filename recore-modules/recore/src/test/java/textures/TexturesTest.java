package textures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterEach;

import com.isthisalis.recore.graphics.shaders.Shader;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.textures.Texture;

import com.isthisalis.recore.graphics.window.*;
import com.isthisalis.recore.util.IO;

public class TexturesTest {

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
  }
}    
