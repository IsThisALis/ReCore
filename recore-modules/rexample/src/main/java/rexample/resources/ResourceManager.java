package rexample.resources;

import recore.graphics.textures.TextureMap;
import recore.graphics.shaders.ShaderProgramMap;
import recore.graphics.render.scene.Scene;

public class ResourceManager {

  ShaderProgramMap shaderProgramMap = Resources.getShaderProgramMap();
  TextureMap textureMap = Resources.getTextureMap();
  Scene scene = Resources.getScene();
  MeshFactory meshFactory = Resources.getMeshFactory();

 public void init() {

   scene.setObjCount(4);
   scene.create();

   scene.addObj(0, meshFactory.newMesh(shaderProgramMap.getObj("ct1"), textureMap.getObj("banana")));
   scene.addObj(1, meshFactory.newMesh(shaderProgramMap.getObj("ct1"), textureMap.getObj("grass_block")));
   scene.addObj(2, meshFactory.newMesh(shaderProgramMap.getObj("ct1"), textureMap.getObj("grass_floor")));
   scene.addObj(3, meshFactory.newMesh(shaderProgramMap.getObj("ct1"), textureMap.getObj("player_red")));
   scene.addObj(4, meshFactory.newMesh(shaderProgramMap.getObj("ct1"), textureMap.getObj("player_red")));
 } 
}
