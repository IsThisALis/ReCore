package org.gfs.recore.graphics.render;

import org.gfs.recore.core.Renderable;

import org.gfs.recore.graphics.shaders.ShaderProgram;

import org.gfs.recore.graphics.textures.Texture;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh implements Renderable {

  VertexBufferObject VBO;
  VertexArrayObject VAO;
  ElementBufferObject EBO;
  
  int[] innerIndices;

    public Mesh(VertexArrayObject vao, VertexBufferObject vbo, ElementBufferObject ebo) {
      VBO = vbo;
      VAO = vao;
      EBO = ebo;
    }

  @Override 
    public void init(float[] vertices, int[] indices, boolean useTexture) {

      VAO.bind();

      VBO.bind();
      VBO.uploadData(vertices);
        
      
      if(useTexture) {
        glDisableVertexAttribArray(1);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Float.BYTES, 0L);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(2, 2, GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(2); } 
      else {
              // Position attribute (xyz - 3 floats)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * Float.BYTES, 0L);
        glEnableVertexAttribArray(0);

              // Color attribute (rgb - 3 floats)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1); }


      EBO.bind();
      EBO.uploadData(indices);

      VAO.unbind();

      System.out.println("New mesh initialized");
    }
  @Override
    public void update(float[] vertices, int[] indices) {

      VBO.bind();
      
      glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);


      EBO.bind();

      glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, 0, indices);
      
    }

  @Override
    public void cleanup() {
      VBO.delete();
      EBO.delete();
    }

  @Override
    public void draw(ShaderProgram shaderProgram, Texture texture) {

      texture.bind();
      VAO.bind();
      shaderProgram.use();

      glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0L);
    }
}
