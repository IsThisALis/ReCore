package org.gfs.recore.example.shaders;

import org.gfs.recore.graphics.shaders.ShaderProgram;
import org.gfs.recore.graphics.shaders.Shader;

import org.gfs.recore.util.IO;

public class ShaderManager {
  Shader v1 = new Shader();
  Shader f1 = new Shader();
  public ShaderProgram program1 = new ShaderProgram();
  //ShaderProgram program2 = new ShaderProgram();

  public void init() {
    v1.createShader("vertex", IO.loadTextFile("samples/shaders/v1.vert"));
    program1.attachShader(v1);
    //program2.attachShader(v1);

    program1.putShader("v1", v1);
    //program2.putShader("v1", v1);

    f1.createShader("fragment", IO.loadTextFile("samples/shaders/f1.frag"));
    program1.attachShader(f1);
    //program2.attachShader(f1);

    program1.putShader("f1", f1);
    //program2.putShader("f1", f1);

    program1.link();
    //program2.link();
  }
}
