package recore.example.resources;

import recore.graphics.render.*;

import recore.graphics.textures.TextureMap;
import recore.graphics.shaders.ShaderProgramMap;
import recore.graphics.render.scene.Scene;

public class ResourceManager {

  VertexArrayObject vao;
  VertexArrayObject vao1;
  VertexArrayObject vao2;

  VertexBufferObject vbo;
  VertexBufferObject vbo1;
  VertexBufferObject vbo2;

  ElementBufferObject ebo;
  ElementBufferObject ebo1;
  ElementBufferObject ebo2;

  Mesh mesh;
  Mesh mesh1;
  Mesh mesh2;
  Mesh mesh3;
  Mesh mesh4;

  ShaderProgramMap shaderProgramMap = Resources.getShaderProgramMap();
  TextureMap textureMap = Resources.getTextureMap();
  Scene scene = Resources.getScene();

 public void init() {

   vao = new VertexArrayObject();
   vao1 = new VertexArrayObject();
   vao2 = new VertexArrayObject();

   vbo = new VertexBufferObject();
   vbo1 = new VertexBufferObject();
   vbo2 = new VertexBufferObject();

   ebo = new ElementBufferObject();
   ebo1 = new ElementBufferObject();
   ebo2 = new ElementBufferObject();

   mesh = new Mesh(vao, vbo, ebo, textureMap.getObj(1), shaderProgramMap.getObj(0));
   mesh1 = new Mesh(vao, vbo, ebo, textureMap.getObj(2), shaderProgramMap.getObj(0));
   mesh2 = new Mesh(vao, vbo, ebo, textureMap.getObj(3), shaderProgramMap.getObj(0));
   mesh3 = new Mesh(vao1, vbo1, ebo1, textureMap.getObj(0), shaderProgramMap.getObj(0));
   mesh4 = new Mesh(vao2, vbo2, ebo2, textureMap.getObj(4), shaderProgramMap.getObj(0));

   scene.setObjCount(4);
   scene.create();
   scene.addObj(0, mesh);
   scene.addObj(1, mesh1);
   scene.addObj(2, mesh2);
   scene.addObj(3, mesh3);
   scene.addObj(4, mesh4);

 } 
}
