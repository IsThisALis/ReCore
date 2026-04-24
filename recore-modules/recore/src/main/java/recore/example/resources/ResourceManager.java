package recore.example.resources;

import recore.graphics.render.*;

import recore.graphics.textures.TextureMap;
import recore.graphics.shaders.ShaderProgramMap;
import recore.graphics.render.scene.Scene;

public class ResourceManager {

  Mesh mesh, mesh1, mesh2, mesh3;

  ShaderProgramMap shaderProgramMap = Resources.getShaderProgramMap();
  TextureMap textureMap = Resources.getTextureMap();
  Scene scene = Resources.getScene();
  MeshFactory meshFactory = Resources.getMeshFactory();

 public void init() {

   scene.setObjCount(4);
   scene.create();

   scene.addObj(0, meshFactory.newMesh(shaderProgramMap.getObj(0), textureMap.getObj(0)));
   scene.addObj(1, meshFactory.newMesh(shaderProgramMap.getObj(0), textureMap.getObj(1)));
   scene.addObj(2, meshFactory.newMesh(shaderProgramMap.getObj(0), textureMap.getObj(2)));
   scene.addObj(3, meshFactory.newMesh(shaderProgramMap.getObj(0), textureMap.getObj(3)));
   scene.addObj(4, meshFactory.newMesh(shaderProgramMap.getObj(0), textureMap.getObj(3)));
 } 
}
