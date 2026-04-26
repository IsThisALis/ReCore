package rexample.resources;

import recore.graphics.shaders.*;

import recore.util.*;

public class ShaderManager {

  Shader vct1 = new Shader();
  Shader fct1 = new Shader();

  ShaderProgram program;

  TextureManager textureManager = Resources.getTextureManager();
  ShaderProgramMap shaderProgramMap = Resources.getShaderProgramMap();

  public void init() {
    
    program = new ShaderProgram();

    vct1.createShader("vertex", IO.loadTextFile("samples/shaders/vct1.vert"));
    
    program.attachShader(vct1);
    program.putShader("vct1", vct1);

    fct1.createShader("fragment", IO.loadTextFile("samples/shaders/fct1.frag"));
    
    program.attachShader(fct1);
    program.putShader("fct1", fct1);
    program.link();
    
    shaderProgramMap.setObjCount(0);
    shaderProgramMap.create();
    shaderProgramMap.addObj(0, program);
  }
}
