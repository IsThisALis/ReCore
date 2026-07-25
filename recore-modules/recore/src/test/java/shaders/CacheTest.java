package shaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import com.isthisalis.recore.graphics.shaders.Shader;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;

import com.isthisalis.recore.graphics.window.Params;
import com.isthisalis.recore.util.IO;

/**
 * CacheTest
 */
public class CacheTest {

  private static ShaderProgram program;

    @BeforeEach
  void init() {
    Params.getParams().setTitle("CacheTest");
    Params.getParams().setWidth(1080);
    Params.getParams().setHeight(720);
    Params.getWindowInst().init();

    program = new ShaderProgram();
    program.setCachePath("test/shadercache/program");
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
