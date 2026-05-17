package shaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterEach;

import recore.graphics.shaders.Shader;
import recore.graphics.shaders.ShaderProgram;
import recore.graphics.window.Params;
import recore.graphics.window.Window;
import recore.util.IO;

public class ShadersTest {

  static ShaderProgram program;
  static Shader shader;

    @BeforeEach
  void init() {
    Window window = Params.getWindowInst();
    Params.getParams().setTitle("ShadersTest");
    Params.getParams();
    Params.getParams().setHeight(720);
    Params.getParams().setVsyncStatus(true);
    window.init();
    program = new ShaderProgram();
    shader = new Shader();
  }

    @AfterEach
  void cleanup() {
    program = null;
    shader = null;
  }

    @RepeatedTest(10)
  void vertex() {
          shader.getShaderType("vertex");
          shader.createShader(IO.loadTextFile("tests/vct1.vert"));
          program.attachShader(shader);
          program.use();
  }

    @RepeatedTest(10)
  void fragment() {
          shader.getShaderType("fragment");
          shader.createShader(IO.loadTextFile("tests/fct1.frag"));
          program.attachShader(shader);
          program.use();
  }
}    
