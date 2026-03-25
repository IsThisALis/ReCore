package org.gfs.recore.example.graphic;

import org.gfs.recore.graphics.shaders.*;

import org.gfs.recore.util.*;

public class ShaderManager {

  Shader vt1 = new Shader();
  Shader ft1 = new Shader();

  public ShaderProgram program;

  TextureManager textureManager = new TextureManager();

  public void init() {
    
    program = new ShaderProgram();

    vt1.createShader("vertex", IO.loadTextFile("samples/shaders/vt1.vert"));
    
    program.attachShader(vt1);
    program.putShader("vt1", vt1);

    ft1.createShader("fragment", IO.loadTextFile("samples/shaders/ft1.frag"));
    
    program.attachShader(ft1);

    program.putShader("ft1", ft1);

    program.link();
    
    textureManager.init(program);
  }


}
