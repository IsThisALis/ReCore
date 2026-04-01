package recore.example.resources;

import recore.graphics.shaders.ShaderProgram;

import recore.graphics.textures.Texture;
import recore.graphics.textures.TextureMap;

import recore.util.IO;

public class TextureManager {

   Texture texture;
   Texture texture1;
   Texture texture2;
   Texture texture3;
   Texture texture4;
  
   IO io = new IO();
   TextureMap textureMap = Resources.getTextureMap();
   ShaderProgram program;

  public void init() {

    program = Resources.getShaderProgramMap().getObj(0);

    textureMap.setObjCount(5);
    textureMap.create();

    texture = new Texture();
    texture1 = new Texture();
    texture2 = new Texture();
    texture3 = new Texture();
    texture4 = new Texture();

    program.use();
    texture.bind();
    texture.getParams().setUniform("ourTexture", 0); 
    textureMap.addObj(0, texture);

    io.loadTexture("samples/textures/banana.png", texture);
    texture.createTexture(program);


    texture1.bind();
    texture1.getParams().setUniform("ourTexture", 0);
    textureMap.addObj(1, texture1);

    io.loadTexture("samples/textures/disassembler-bottom.png", texture1);
    texture1.createTexture(program);


    texture2.bind();
    texture2.getParams().setUniform("ourTexture", 0);
    textureMap.addObj(2, texture2);

    io.loadTexture("samples/textures/disassembler-spinner.png", texture2);
    texture2.createTexture(program);


        // Bindings and settings
    texture3.bind();
    texture3.getParams().setUniform("ourTexture", 0);
    textureMap.addObj(3, texture3);

        // Creating texture
    io.loadTexture("samples/textures/disassembler.png", texture3);
    texture3.createTexture(program);


    texture4.bind();
    texture4.getParams().setUniform("ourTexture", 0);
    textureMap.addObj(4, texture4);

    io.loadTexture("samples/textures/grass_floor.png", texture4);
    texture4.createTexture(program);

    }
}
