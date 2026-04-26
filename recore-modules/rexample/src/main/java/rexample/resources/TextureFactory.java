package rexample.resources;

import recore.graphics.shaders.ShaderProgram;
import recore.graphics.textures.Texture;

import recore.util.IO;

public class TextureFactory {

  IO io = new IO();

  public Texture newTexture(String path, ShaderProgram program) {
    program.use();
    Texture texture = new Texture();

    texture.params.setUniform("ourTexture", 0);
    texture.bind();

    io.loadTexture(path, texture);
    texture.createTexture(program);

    return texture;
  }
}
