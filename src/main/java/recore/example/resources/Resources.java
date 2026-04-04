package recore.example.resources;

import recore.graphics.textures.TextureMap;
import recore.graphics.shaders.ShaderProgramMap;
import recore.graphics.render.scene.Scene;;

public class Resources {

  private static ShaderProgramMap shaderProgramMap = new ShaderProgramMap();
  private static ShaderManager shaderManager = new ShaderManager();
  private static TextureMap textureMap = new TextureMap();
  private static TextureFactory textureFactory = new TextureFactory();
  private static TextureManager textureManager = new TextureManager();
  private static Scene scene = new Scene();
  private static ResourceManager resourceManager = new ResourceManager();

  public static ShaderManager getShaderManager() {
    return shaderManager;
  }

  public static TextureManager getTextureManager() {
    return textureManager;
  }

  public static TextureMap getTextureMap() {
    return textureMap;
  }

  public static ShaderProgramMap getShaderProgramMap() {
    return shaderProgramMap;
  }

  public static Scene getScene() {
    return scene;
  }

  public static ResourceManager getResourceManager() {
    return resourceManager;
  }

  public static TextureFactory getTextureFactory() {
    return textureFactory;
  }
}
