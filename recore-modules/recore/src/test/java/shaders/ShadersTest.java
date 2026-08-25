package shaders;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.isthisalis.recore.graphics.shaders.Shader;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.window.Configuration;
import com.isthisalis.recore.graphics.window.Window;
import com.isthisalis.recore.util.IO;

public class ShadersTest {

  static ShaderProgram program;
  static Shader shader;

    @BeforeAll
  static void init() {
    Window window = new Window();
    window.init(Configuration.builder()
    .width(320)
    .height(240)
    .vsync(true)
    .title("ShadersTest")
    .build(), null);
    program = new ShaderProgram();
    shader = new Shader();
  }

    @AfterAll
  static void cleanup() {
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
