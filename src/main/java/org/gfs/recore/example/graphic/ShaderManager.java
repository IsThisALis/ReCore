package org.gfs.recore.example.graphic;

import org.gfs.recore.graphics.shaders.*;

import org.gfs.recore.util.*;

public class ShaderManager {

  Shader vt1 = new Shader();
  Shader ft1 = new Shader();

  Shader f1 = new Shader();
  Shader v1 = new Shader();

  public ShaderProgram program1;
  public ShaderProgram program2;
  TextureManager textureManager = new TextureManager();

  public void init() {
    program1 = new ShaderProgram();
    program2 = new ShaderProgram();

    vt1.createShader("vertex", IO.loadTextFile("samples/shaders/vt1.vert"));
    //v1.createShader("vertex", IO.loadTextFile("samples/shaders/v1.vert"));
    program1.attachShader(vt1);
    program2.attachShader(vt1);

    program1.putShader("vt1", vt1);
    program2.putShader("vt1", vt1);

    ft1.createShader("fragment", IO.loadTextFile("samples/shaders/ft1.frag"));
    //f1.createShader("fragment", IO.loadTextFile("samples/shaders/f1.frag"));
    program1.attachShader(ft1);
    program2.attachShader(f1);

    program1.putShader("ft1", ft1);
    program2.putShader("ft1", ft1);

    program1.link();
    program2.link();
    
    textureManager.init(program1, program2);
  }


}
