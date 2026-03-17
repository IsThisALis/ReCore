package org.gfs.recore.example.graphic;

import org.gfs.recore.graphics.shaders.ShaderProgram;

import org.gfs.recore.graphics.textures.Texture;

import org.gfs.recore.util.IO;

public class TextureManager {

  private IO io = new IO();

  public Texture texture;
  public Texture texture1;
  public Texture texture2;
  public Texture texture3;

  public void init(ShaderProgram program, ShaderProgram program1, ShaderProgram program2, ShaderProgram program3) {
    texture = new Texture();
    texture1 = new Texture();
    texture2 = new Texture();
    texture3 = new Texture();

    texture.getParams().setUniform("ourTexture", 0);
    texture1.getParams().setUniform("ourTexture", 1);
    texture2.getParams().setUniform("ourTexture", 2);
    texture3.getParams().setUniform("ourTexture", 3);

    program.use();
    io.loadTexture("samples/textures/beryllium-wall.png", texture);
    texture.createTexture(program);

    program1.use();
    io.loadTexture("samples/textures/disassembler-bottom.png", texture1);
    texture1.createTexture(program1);

    program2.use();
    io.loadTexture("samples/textures/disassembler-spinner.png", texture2);
    texture2.createTexture(program2);

    program3.use();
    io.loadTexture("samples/textures/disassembler.png", texture3);
    texture3.createTexture(program3);
  }
}
