package org.gfs.recore.example.graphic;

import org.gfs.recore.graphics.shaders.ShaderProgram;

import org.gfs.recore.graphics.textures.Texture;

import org.gfs.recore.util.IO;

public class TextureManager {

  private IO io = new IO();

  public Texture texture;
  public Texture texture1;

  public void init(ShaderProgram program, ShaderProgram program1) {
    texture = new Texture();
    texture1 = new Texture();

    texture.getParams().setUniform("ourTexture", 0);
    texture1.getParams().setUniform("ourTexture", 1);

    program.use();
    io.loadTexture("samples/textures/beryllium-wall.png", texture);
    texture.createTexture(program);

    program1.use();
    io.loadTexture("samples/textures/surge-crucible.png", texture1);
    texture1.createTexture(program1);
  }
}
