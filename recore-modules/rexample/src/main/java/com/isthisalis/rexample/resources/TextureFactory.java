package com.isthisalis.rexample.resources;

import com.isthisalis.recore.graphics.shaders.ShaderProgram;
import com.isthisalis.recore.graphics.textures.Texture;

import com.isthisalis.recore.util.IO;

public class TextureFactory {

  IO io = new IO();

  public Texture newTexture(String path, ShaderProgram program) {
    Texture texture = new Texture();
    texture.getParams().setUniform("ourTexture", 0);

    io.loadTexture(path, texture);
    texture.createTexture(program);
    return texture;
  }
}
