package org.gfs.recore.example.graphic;

import org.gfs.recore.graphics.shaders.ShaderProgram;

import org.gfs.recore.graphics.textures.Texture;

import org.gfs.recore.util.IO;

public class TextureManager {

  private IO io = new IO();

   Texture texture;
   Texture texture1;
   Texture texture2;
   Texture texture3;
   Texture texture4;
  

  public void init(ShaderProgram program) {
    texture = new Texture();
    texture1 = new Texture();
    texture2 = new Texture();
    texture3 = new Texture();
    texture4 = new Texture();

    program.use();
    texture.bind();
    texture.getParams().setUniform("ourTexture", 0); 

    io.loadTexture("samples/textures/beryllium-wall.png", texture);
    texture.createTexture(program);

    texture1.bind();
    texture1.getParams().setUniform("ourTexture", 0);

    io.loadTexture("samples/textures/disassembler-bottom.png", texture1);
    texture1.createTexture(program);

    texture2.bind();
    texture2.getParams().setUniform("ourTexture", 0);

    io.loadTexture("samples/textures/disassembler-spinner.png", texture2);
    texture2.createTexture(program);

        // Bindings and settings
    texture3.bind();
    texture3.getParams().setUniform("ourTexture", 0);

        // Creating texture
    io.loadTexture("samples/textures/disassembler.png", texture3);
    texture3.createTexture(program);

    texture4.bind();
    texture4.getParams().setUniform("ourTexture", 0);

    io.loadTexture("samples/textures/grass_floor.png", texture4);
    texture4.createTexture(program);
  }
}
