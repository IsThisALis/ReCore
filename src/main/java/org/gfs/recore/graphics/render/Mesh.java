package org.gfs.recore.graphics.render;

import org.gfs.recore.core.Renderable;

import org.gfs.recore.graphics.shaders.ShaderProgram;

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
    public void init(float[] vertices, int[] indices) {

      VAO.bind();

      VBO.bind();

              // Position attribute (xyz - 3 floats)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * Float.BYTES, 0L);
        glEnableVertexAttribArray(0);

              // Color attribute (rgb - 3 floats)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

              // Texture coordinate attribute (uv - 2 floats)
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 8 * Float.BYTES, 6 * Float.BYTES);
        glEnableVertexAttribArray(2);

      EBO.bind();
      EBO.uploadData(indices);
   
      VBO.uploadData(vertices);

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
    public void draw(ShaderProgram shaderProgram) {
      VAO.bind();
      shaderProgram.use();

      glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0L);
    }
}
