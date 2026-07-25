package com.isthisalis.rexample.resources;

import com.isthisalis.recore.graphics.textures.TextureMap;
import com.isthisalis.recore.graphics.shaders.ShaderProgramMap;
import com.isthisalis.recore.graphics.render.scene.Scene;
import com.isthisalis.recore.graphics.material.*;

public class ResourceManager {

  ShaderProgramMap shaderProgramMap = Resources.getShaderProgramMap();
  TextureMap textureMap = Resources.getTextureMap();
  Scene scene = Resources.getScene();
  MaterialMap materials = Resources.getMaterialMap();
  MeshFactory meshFactory = Resources.getMeshFactory();

 public void init() {

   scene.setObjCount(4);
   scene.create();

   materials.addObj("grass_floor", new Material(textureMap.getObj("grass_floor"), shaderProgramMap.getObj("ct1")));
   materials.addObj("banana", new Material(textureMap.getObj("banana"), shaderProgramMap.getObj("ct1")));
   materials.addObj("player", new Material(textureMap.getObj("player_red"), shaderProgramMap.getObj("ct1")));
   materials.addObj("grass_block", new Material(textureMap.getObj("grass_block"), shaderProgramMap.getObj("ct1")));

   scene.addObj(0, meshFactory.newMesh(shaderProgramMap.getObj("ct1")));
   scene.addObj(1, meshFactory.newMesh(shaderProgramMap.getObj("ct1")));
   scene.addObj(2, meshFactory.newMesh(shaderProgramMap.getObj("ct1")));
   scene.addObj(3, meshFactory.newMesh(shaderProgramMap.getObj("ct1")));
   scene.addObj(4, meshFactory.newMesh(shaderProgramMap.getObj("ct1")));


 } 
}
