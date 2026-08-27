package com.isthisalis.recore.graphics.render;

import com.isthisalis.recore.core.util.buffers.ElementBufferObject;
import com.isthisalis.recore.core.util.buffers.VertexArrayObject;
import com.isthisalis.recore.core.util.buffers.VertexBufferObject;

import lombok.Getter;

// OpenGL imports
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.logging.Logger;

public class Mesh {

      // Logger wrap 
  private Logger log = Logger.getLogger(Mesh.class.getName());

      // Buffers for data
  private @Getter VertexBufferObject VBO;
  private @Getter VertexArrayObject VAO;
  private @Getter ElementBufferObject EBO;

      // Array values
  private @Getter int[] indices;
  private @Getter float[] vertices;


      /**
       * Advanced constructor for creating mesh
       * Renders with texture
       * @param vao VertexArrayObject to attach 
       * @param vbo VertexBufferObject to attach 
       * @param ebo ElementBufferObject to attach 
       * @param shaderProgram ShaderProgram to attach 
       */
    public Mesh() {
          // Attaches buffers
      VBO = new VertexBufferObject();
      VAO = new VertexArrayObject();
      EBO = new ElementBufferObject();
      log.info("New mesh initialized");
    } 


    /**
     * Imitializes mesh
     * @param vertices coordinates and color/UV of object
     * @param indices indices of object
     * @param useTexture use texture or not, if true requires to load texture and use UV in vertices instead of rgb
     */
    public void init(float[] vertices, int[] indices, boolean useTexture) {
      this.indices = indices;
      this.vertices = vertices;

          // Binds VAO to set data parameters
      VAO.bind();

          // Binds VBO
      VBO.bind();
          // Uploads mesh data to VBO
      VBO.uploadData(vertices);
        
      
      if(useTexture) {
              // Position attribute (xyz - 3 floats)
        glDisableVertexAttribArray(1);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Float.BYTES, 0L);
        glEnableVertexAttribArray(0);

              // UV mapping attribute (uv - 2 floats)
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(2); } 
      else {
              // Position attribute (xyz - 3 floats)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * Float.BYTES, 0L);
        glEnableVertexAttribArray(0);

              // Color attribute (rgb - 3 floats)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1); }

          // Binds EBO
      EBO.bind();
          // Uploads render data to EBO
      EBO.uploadData(indices);
          // Unbinds VAO (no use now)
      VAO.unbind();
    }


    /**
     * Updates data in buffers, can be used for controlling object 
     *  @param vertices new position and uv mapping attributes
     *  @param indices new indices data 
     */
    public void update(float[] vertices, int[] indices) {
      this.vertices = vertices;
      this.indices = indices;

          // Binds VBO to update data
      VBO.bind();

          // Updates VBO data
      glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);

          // Binds EBO to update data
      EBO.bind();

          // Updates EBO data
      glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, 0, indices);
    }


    /**
     * Deletes all data in buffers.
     */
    public void cleanup() {
      VBO.delete();
      EBO.delete();
      VAO.delete();
    }

     
    public void bind() {
          // Binds VAO
      VAO.bind();
    }

    public void draw() {
      glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0L); 
    }
  }
