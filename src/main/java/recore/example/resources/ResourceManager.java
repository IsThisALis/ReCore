package recore.example.resources;

import recore.graphics.render.*;

import recore.graphics.textures.TextureMap;
import recore.graphics.shaders.ShaderProgramMap;
import recore.graphics.render.scene.Scene;

public class ResourceManager {

  VertexArrayObject vao, vao1, vao2, vao3;

  VertexBufferObject vbo, vbo1, vbo2, vbo3;

  ElementBufferObject ebo, ebo1, ebo2, ebo3;

  Mesh mesh, mesh1, mesh2, mesh3;

  ShaderProgramMap shaderProgramMap = Resources.getShaderProgramMap();
  TextureMap textureMap = Resources.getTextureMap();
  Scene scene = Resources.getScene();

 public void init() {

   vao = new VertexArrayObject();
   vao1 = new VertexArrayObject();
   vao2 = new VertexArrayObject();
   vao3 = new VertexArrayObject();

   vbo = new VertexBufferObject();
   vbo1 = new VertexBufferObject();
   vbo2 = new VertexBufferObject();
   vbo3 = new VertexBufferObject();

   ebo = new ElementBufferObject();
   ebo1 = new ElementBufferObject();
   ebo2 = new ElementBufferObject();
   ebo3 = new ElementBufferObject();

   mesh = new Mesh(vao, vbo, ebo, textureMap.getObj(0), shaderProgramMap.getObj(0));
   mesh1 = new Mesh(vao1, vbo1, ebo1, textureMap.getObj(1), shaderProgramMap.getObj(0));
   mesh2 = new Mesh(vao2, vbo2, ebo, textureMap.getObj(2), shaderProgramMap.getObj(0));
   mesh3 = new Mesh(vao3, vbo3, ebo3, textureMap.getObj(3), shaderProgramMap.getObj(0));

   scene.setObjCount(5);
   scene.create();
   scene.addObj(0, mesh);
   scene.addObj(1, mesh1);
   scene.addObj(2, mesh2);
   scene.addObj(3, mesh3);
 } 
}
