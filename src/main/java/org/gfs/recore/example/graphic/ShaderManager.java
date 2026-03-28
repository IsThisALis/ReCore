package org.gfs.recore.example.graphic;

import org.gfs.recore.graphics.shaders.*;

import org.gfs.recore.util.*;

public class ShaderManager {

  Shader vct1 = new Shader();
  Shader fct1 = new Shader();

  public ShaderProgram program;

  TextureManager textureManager = new TextureManager();

  public void init() {
    
    program = new ShaderProgram();

    vct1.createShader("vertex", IO.loadTextFile("samples/shaders/vct1.vert"));
    
    program.attachShader(vct1);
    program.putShader("vct1", vct1);

    fct1.createShader("fragment", IO.loadTextFile("samples/shaders/fct1.frag"));
    
    program.attachShader(fct1);

    program.putShader("fct1", fct1);

    program.link();
    
    textureManager.init(program);
  }


}
