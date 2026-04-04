package recore.example.resources;

import recore.graphics.shaders.ShaderProgram;

import recore.graphics.textures.TextureMap;

public class TextureManager {

   TextureMap textureMap = Resources.getTextureMap();
   TextureFactory textureFactory = Resources.getTextureFactory();
   ShaderProgram program;

  public void init() {

    program = Resources.getShaderProgramMap().getObj(0);

    textureMap.setObjCount(5);
    textureMap.create();

    textureMap.addObj(0, textureFactory.newTexture("samples/textures/banana.png", program));
    textureMap.addObj(1, textureFactory.newTexture("samples/textures/grass_block.png", program));
    textureMap.addObj(2, textureFactory.newTexture("samples/textures/grass_floor.png", program));
    textureMap.addObj(3, textureFactory.newTexture("samples/textures/player.png", program));
    }
}
