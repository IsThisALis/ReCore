package rexample.resources;

import recore.graphics.shaders.*;

import recore.util.IO;

public class ShaderManager {

  Shader vct1 = new Shader();
  Shader fct1 = new Shader();

  Shader vct2 = new Shader();
  Shader fct2 = new Shader();

  ShaderProgram program;
  ShaderProgram program1;

  ShaderProgramMap shaderProgramMap = Resources.getShaderProgramMap();

  public void init() {
    
    program = new ShaderProgram();
    program1 = new ShaderProgram();

    program.setCachePath("cache/shaders/program");
    program1.setCachePath("cache/shaders/program1");

    if (program.hasCache()) program.loadCache();
    if (program1.hasCache()) program1.loadCache();

    if (program.hasCache() && program1.hasCache()) {
      shaderProgramMap.addObj("ct1", program);
      shaderProgramMap.addObj("ct2", program1);
      return;
    }

    vct1.getShaderType("vertex");
    vct1.createShader(IO.loadTextFile("samples/shaders/vct1.vert"));
    
    program.attachShader(vct1);
    program.putShader("vct1", vct1);

    fct1.getShaderType("fragment");
    fct1.createShader(IO.loadTextFile("samples/shaders/fct1.frag"));
    
    program.attachShader(fct1);
    program.putShader("fct1", fct1);
    program.link();

    vct2.getShaderType("vertex");
    vct2.createShader(IO.loadTextFile("samples/shaders/vct1.vert"));
    
    program1.attachShader(vct1);
    program1.putShader("vct1", vct1);

    fct2.getShaderType("fragment");
    fct2.createShader(IO.loadTextFile("samples/shaders/fct1.frag"));
    
    program1.attachShader(fct1);
    program1.putShader("fct1", fct1);
    program1.link();

    if (!program.hasCache()) program.cache();
    if (!program1.hasCache()) program1.cache();

    
    shaderProgramMap.addObj("ct1", program);
    shaderProgramMap.addObj("ct2", program1);

    vct1 = null;
    vct2 = null;

    fct1 = null;
    fct2 = null;

    shaderProgramMap = null;
  }
}
