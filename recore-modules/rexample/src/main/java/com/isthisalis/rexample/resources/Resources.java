package com.isthisalis.rexample.resources;

import com.isthisalis.recore.graphics.textures.TextureMap;
import com.isthisalis.recore.graphics.shaders.ShaderProgramMap;
import com.isthisalis.recore.graphics.material.MaterialMap;
import com.isthisalis.recore.graphics.render.scene.Scene;

public class Resources {

  private static ShaderProgramMap shaderProgramMap = new ShaderProgramMap();
  private static ShaderManager shaderManager = new ShaderManager();
  private static TextureMap textureMap = new TextureMap();
  private static TextureFactory textureFactory = new TextureFactory();
  private static MeshFactory meshFactory = new MeshFactory();
  private static TextureManager textureManager = new TextureManager();
  private static Scene scene = new Scene();
  private static MaterialMap materialMap = new MaterialMap();
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

  public static MeshFactory getMeshFactory() {
    return meshFactory;
  }

  public static MaterialMap getMaterialMap() {
    return materialMap;
  }
}
