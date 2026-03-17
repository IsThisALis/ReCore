package org.gfs.recore.example.graphic;

import org.gfs.recore.graphics.shaders.*;

import org.gfs.recore.util.*;

public class ShaderManager {

  Shader vt1 = new Shader();
  Shader ft1 = new Shader();

  public ShaderProgram program;
  public ShaderProgram program1;
  public ShaderProgram program2;
  public ShaderProgram program3;

  TextureManager textureManager = new TextureManager();

  public void init() {
    
    program = new ShaderProgram();
    program1 = new ShaderProgram();
    program2 = new ShaderProgram();
    program3 = new ShaderProgram();

    vt1.createShader("vertex", IO.loadTextFile("samples/shaders/vt1.vert"));
    
    program.attachShader(vt1);
    program1.attachShader(vt1);
    program2.attachShader(vt1);
    program3.attachShader(vt1);

    /*program.putShader("vt1", vt1);
    program1.putShader("vt1", vt1);*/

    ft1.createShader("fragment", IO.loadTextFile("samples/shaders/ft1.frag"));
    
    program.attachShader(ft1);
    program1.attachShader(ft1);
    program2.attachShader(ft1);
    program3.attachShader(ft1);

    /*program.putShader("ft1", ft1);
    program1.putShader("ft1", ft1);*/

    program.link();
    program1.link();
    program2.link();
    program3.link();
    
    textureManager.init(program, program1, program2, program3);
  }


}
