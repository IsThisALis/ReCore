package shaders;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import com.isthisalis.recore.graphics.shaders.Shader;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.window.Configuration;
import com.isthisalis.recore.graphics.window.Window;
import com.isthisalis.recore.util.IO;

import app.Application;

/**
 * CacheTest
 */
public class CacheTest {

  private static ShaderProgram program;

    @BeforeAll
  static void init() {
    Window window = new Window();
    window.init(Configuration.builder()
    .width(320)
    .height(240)
    .vsync(true)
    .title("CacheTest")
    .build(), new Application());
    program = new ShaderProgram();
    program.setName("sp1");
  }

    @RepeatedTest(5)
  void load() {
    if (program.hasCache()) { System.out.println("Found cache!"); program.loadCache(); return; }
    else {
    System.out.println("No cache found!");
    Shader s1 = new Shader();
    Shader s2 = new Shader();

    s1.getShaderType("vertex");
    s1.createShader(IO.loadTextFile("tests/vct1.vert"));

    s2.getShaderType("fragment");
    s2.createShader(IO.loadTextFile("tests/fct1.frag"));
    
    program.attachShader(s1);
    program.attachShader(s2);
    program.link();

    program.cache();
    }
  }
}
